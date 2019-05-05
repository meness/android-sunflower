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

package com.purpletealabs.stores.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.remote.mapper.EntityMapper
import com.purpletealabs.stores.remote.model.StoreModel
import com.purpletealabs.stores.data.StoreRemote

/**
 * Repository module for handling data operations.
 */
class StoreRemoteImpl private constructor(
    private val storeService: StoreService,
    private val mapper: EntityMapper<StoreModel, Store>
):StoreRemote {

    override fun getStores(): LiveData<List<Store>> {
        return Transformations.map(storeService.getStores()) {
            it.stores.map { storeModel ->
                mapper.mapFromRemote(storeModel)
            }
        }
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: StoreRemoteImpl? = null

        fun getInstance(storeService: StoreService, mapper: EntityMapper<StoreModel, Store>) =
            instance ?: synchronized(this) {
                instance
                    ?: StoreRemoteImpl(storeService, mapper).also { instance = it }
            }
    }
}
