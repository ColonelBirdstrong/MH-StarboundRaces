/*
 * Copyright 2017 Kaidan Gustave
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.kgustave.mhinstaller

import me.kgustave.mhinstaller.entities.ModType
import me.kgustave.mhinstaller.util.await
import me.kgustave.mhinstaller.util.okClient
import me.kgustave.mhinstaller.util.request
import me.kgustave.mhinstaller.util.tryWith
import java.io.File
import java.nio.file.Paths
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * @author Kaidan Gustave
 */
object Downloader {
    val okClient = okClient {
        followRedirects(true)
    }

    suspend fun download(vararg modTypes: ModType) {
        val temp = createTempDir()

        val sbMods = File(Main.os.sbModPath)
        if(sbMods.exists())
            throw IllegalStateException("Starbound Mods Folder was not found!")

        for(modType in modTypes) {
            val call = okClient.request {
                url("https://github.com/ColonelBirdstrong/MH-StarboundRaces/archives/master.zip")
                get()
            }

            tryWith(call.await()) res@ { res ->
                tryWith(res.body()) body@ { body ->
                    if(body == null)
                        return@body

                    val file = createTempFile(prefix = "download", suffix = "zip", directory = temp)

                    tryWith(ZipOutputStream(file.outputStream(), Charsets.UTF_8)) zipOut@ { zipOut ->
                        tryWith(ZipInputStream(body.byteStream())) zipIn@ { zipIn ->
                            zipOut.putNextEntry(zipIn.nextEntry)
                        }
                    }

                    tryWith(ZipFile(file)) { zip ->
                        val entries = zip.entries().toList()
                        entries.forEach { entry ->
                            val entryOutput = Paths.get(sbMods.path, entry.name).toFile()

                            if(entry.isDirectory) {
                                return@forEach handleDownloadedDir(entryOutput)
                            }

                            if(entryOutput.exists())
                                entryOutput.delete()

                            entryOutput.createNewFile()
                            tryWith(entryOutput.writer(Charsets.UTF_8)) { writer ->
                                writer.write(zip.getInputStream(entry).readBytes().toString(Charsets.UTF_8))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleDownloadedDir(dir: File) {
        dir.mkdirs() // TODO Check result
    }
}
