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
package me.kgustave.mhinstaller.entities

import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.Window

/**
 * @author Kaidan Gustave
 */
data class Dimensions(val height: Double, val width: Double) {
    constructor(height: Number, width: Number): this(height.toDouble(), width.toDouble())

    override fun toString(): String = "$height x $width"
}

infix fun Number.x(other: Number): Dimensions = Dimensions(this, other)

inline var Window.dimensions: Dimensions
    get() = height x width
    set(value) {
        height = value.height
        width = value.width
    }

inline var Stage.max: Dimensions
    get() = maxHeight x maxWidth
    set(value) {
        maxHeight = value.height
        maxWidth = value.width
    }

inline var Stage.min: Dimensions
    get() = minHeight x minWidth
    set(value) {
        minHeight = value.height
        minWidth = value.width
    }
