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

package com.purpletealabs.stores.data.source

import androidx.lifecycle.LiveData
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.data.StoreCache
import com.purpletealabs.stores.data.StoreDataStore

/**
 * Implementation of the [StoreDataStore] interface to provide a means of communicating
 * with the local data source
 */
class StoreCacheDataStore constructor(private val storeCache: StoreCache) :
    StoreDataStore {

    /**
     * Save a given [List] of [Store] instances to the cache
     */
    override fun saveStores(stores: List<Store>): List<Long> {
        val savedStoresIds = storeCache.saveStores(stores)
        if (savedStoresIds.isNotEmpty()) storeCache.setLastCacheTime(System.currentTimeMillis())
        return savedStoresIds
    }

    /**
     * Retrieve a list of [Store] instance from the cache
     */
    override fun getStores(storeId: Int): LiveData<List<Store>> {
        return storeCache.getStores(storeId)
    }
}