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
package me.kgustave.mhinstaller.util

import okhttp3.*
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine

inline fun okClient(block: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient
    = OkHttpClient.Builder().apply(block).build()

inline fun OkHttpClient.request(block: Request.Builder.() -> Unit): Call = newCall(Request.Builder().apply(block).build())

suspend fun Call.await() = suspendCoroutine<Response> { cont ->
    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            cont.resumeWithException(e)
        }

        override fun onResponse(call: Call, response: Response) {
            cont.resume(response)
        }
    })
}
