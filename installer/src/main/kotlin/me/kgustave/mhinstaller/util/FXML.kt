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

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import me.kgustave.mhinstaller.Main

inline fun stage(block: Stage.() -> Unit = {}): Stage = Stage().apply(block)

inline fun <reified P: Parent> scene(root: P, block: Scene.() -> Unit = {}): Scene = Scene(root).apply(block)

inline fun <reified E: Parent> loadFXML(path: String, loader: FXMLLoader? = null, context: Any = Main): E {
    val url = requireNotNull(context::class.java.getResource(path)) { "Could not locate resource '$path'!" }
    return if(loader == null) { FXMLLoader.load<E>(url) } else { url.openStream().use { loader.load<E>(it) } }
}

inline fun <reified E: Parent> FXMLLoader.load(path: String, context: Any = Main): E = loadFXML(path, this, context)
inline fun <reified T> FXMLLoader.controller(): T = getController()

