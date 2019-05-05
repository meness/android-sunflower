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
 *//*


package com.purpletealabs.stores.presentation

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.data.StoreRepository
import java.util.Date

class StoreListDataSource constructor(
    private val storeRepository: StoreRepository
) : ItemKeyedDataSource<Int, Store>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Store>
    ) {
        params.requestedInitialKey?.let {
            getAsteroids(it, callback)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Store>
    ) {
        getAsteroids(params.key, callback)
    }

    private fun getAsteroids(storeId: Int, callback: LoadCallback<Store>) {
        storeRepository.getStores(storeId).
        storeRepository.getStores(storeId).storeRepository.execute(AsteroidSubscriber {
            callback.onResult(it)
        }, GetAsteroidsParams(startDate, endDate))
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Store>
    ) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: Store): Int {
        return item.storeId
    }

    class Factory constructor(private val dataSource: StoreListDataSource) :
        DataSource.Factory<Date, Store>() {

        val liveDataSource = MutableLiveData<StoreListDataSource>()

        override fun create(): StoreListDataSource {
            liveDataSource.postValue(dataSource)
            return dataSource
        }
    }
}*/
