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

import javafx.application.Application
import javafx.scene.Parent
import javafx.stage.Stage
import me.kgustave.mhinstaller.entities.OS
import me.kgustave.mhinstaller.entities.min
import me.kgustave.mhinstaller.entities.x
import me.kgustave.mhinstaller.util.loadFXML
import me.kgustave.mhinstaller.util.scene

/**
 * @author Kaidan Gustave
 */
@Suppress("MemberVisibilityCanPrivate")
object Main : Application() {

    private const val APP_TITLE = "MH Starbound Mods Installer"

    @JvmStatic
    fun main(args: Array<String>) { launch(*args) }

    lateinit var os: OS

    override fun init() {
        os = OS.get()
    }

    override fun start(primaryStage: Stage) {
        if(os != OS.UNSUPPORTED)
            displayInstaller(primaryStage)
        else
            displayUnsupported(primaryStage)
    }

    override fun stop() {

    }

    private fun displayInstaller(primaryStage: Stage) = with(primaryStage) {
        scene = scene(loadFXML<Parent>("/installer.fxml"))
        title = APP_TITLE
        min = 250 x 350

        show()
    }

    private fun displayUnsupported(primaryStage: Stage)  = with(primaryStage) {
        scene = scene(loadFXML<Parent>("/installer.fxml"))
        title = APP_TITLE
        min = 250 x 350

        show()
    }
}
