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

package com.purpletealabs.stores.data

import androidx.paging.DataSource
import com.purpletealabs.stores.cache.Store

interface StoreCache {
    /**
     * Save a given list of Stores to the cache
     */
    fun saveStores(stores: List<Store>): List<Long>

    /**
     * Retrieve a list of Stores, from the cache
     */
    fun getStores(storeId: Int): DataSource.Factory<Int, Store>

    /**
     * Checks if an element exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Sets last cache time.
     *
     * @param lastCache The last cache time.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean
}