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

package com.purpletealabs.stores.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
class PreferencesHelper constructor(context: Context) {

    companion object {
        private const val PREF_STORE_PACKAGE_NAME = "com.purpletealabs.stores.preferences"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val storePref: SharedPreferences

    init {
        storePref =
            context.getSharedPreferences(PREF_STORE_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = storePref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = storePref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}
