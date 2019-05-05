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
import com.purpletealabs.stores.data.StoreDataStore
import com.purpletealabs.stores.data.StoreRemote

/**
 * Implementation of the [StoreDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class StoreRemoteDataStore constructor(private val storeRemote: StoreRemote) :
    StoreDataStore {

    override fun saveStores(stores: List<Store>): List<Long> {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [Store] instances from the API
     */
    override fun getStores(storeId: Int): LiveData<List<Store>> {
        return storeRemote.getStores()
    }
}