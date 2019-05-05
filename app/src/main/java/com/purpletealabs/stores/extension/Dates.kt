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

import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.plusDays(days: Int): Date {
    return Calendar.getInstance(Locale.US).apply {
        time = this@plusDays
        add(Calendar.DATE, days)
    }.time
}