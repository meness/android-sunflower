/*
 * Copyright 2019 PurpleTeaLabs
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

package com.purpletealabs.stores.extension

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executor

/**
 * Equivalent to [AppCompatTextView.setText] but using prefetch mechanism.
 * It reduces the measure time by 95%!
 * Note: Use this method inside of [RecyclerView]
 */
fun AppCompatTextView.setPrefetchText(text: CharSequence, executor: Executor? = null) {
    setTextFuture(PrecomputedTextCompat.getTextFuture(text, textMetricsParamsCompat, executor))
}