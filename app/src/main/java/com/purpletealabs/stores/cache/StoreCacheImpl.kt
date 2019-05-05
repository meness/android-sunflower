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

import com.purpletealabs.stores.data.StoreCache

/**
 * Repository module for handling data operations.
 */
class StoreCacheImpl private constructor(
    private val storeDao: StoreDao,
    private val preferencesHelper: PreferencesHelper
) : StoreCache {

    override fun saveStores(stores: List<Store>): List<Long> {
        return storeDao.insertAll(stores)
    }

    override fun isCached(): Boolean {
        return storeDao.storedStoresCount() > 0
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    override fun getStores(storeId: Int) = storeDao.getStores(storeId)

    companion object {

        const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

        // For Singleton instantiation
        @Volatile
        private var instance: StoreCacheImpl? = null

        fun getInstance(plantDao: StoreDao, preferencesHelper: PreferencesHelper) =
            instance ?: synchronized(this) {
                instance
                    ?: StoreCacheImpl(plantDao, preferencesHelper).also { instance = it }
            }
    }
}
