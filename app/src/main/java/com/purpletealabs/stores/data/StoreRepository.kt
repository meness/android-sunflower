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

import androidx.lifecycle.LiveData
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.cache.StoreCacheImpl
import com.purpletealabs.stores.data.source.StoreCacheDataStore
import com.purpletealabs.stores.data.source.StoreRemoteDataStore

/**
 * Repository module for handling data operations.
 */
class StoreRepository private constructor(
    private val storeCacheRepository: StoreCacheDataStore,
    private val storeRemoteRepository: StoreRemoteDataStore,
    private val storeCacheImpl: StoreCacheImpl
) : StoreDataStore {
    override fun saveStores(stores: List<Store>): List<Long> {
        return storeCacheRepository.saveStores(stores)
    }

    override fun getStores(storeId: Int): LiveData<List<Store>> {
        // TODO: Save data to cache if cache data source retrieved.
        val dataSource = retrieveDataStore()
        if (dataSource is StoreRemoteDataStore){
            dataSource.getStores(storeId)
        }

        return dataSource.getStores(storeId)
    }

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    private fun retrieveDataStore(): StoreDataStore {
        val shouldFetchCacheDataStore = storeCacheImpl.isCached() && !storeCacheImpl.isExpired()
        if (shouldFetchCacheDataStore) return storeCacheRepository
        else return storeRemoteRepository
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: StoreRepository? = null

        fun getInstance(
            storeCacheRepository: StoreCacheDataStore,
            storeRemoteRepository: StoreRemoteDataStore,
            storeCacheImpl: StoreCacheImpl
        ) =
            instance ?: synchronized(this) {
                instance
                    ?: StoreRepository(
                        storeCacheRepository,
                        storeRemoteRepository,
                        storeCacheImpl
                    ).also { instance = it }
            }
    }
}
