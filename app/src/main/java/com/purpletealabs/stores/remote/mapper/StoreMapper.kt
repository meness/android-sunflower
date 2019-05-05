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

package com.purpletealabs.stores.remote.mapper

import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.remote.model.StoreModel

/**
 * Map a [StoreModel] to and from a [Store] instance when data is moving between
 * this later and the Data layer
 */
class StoreMapper :
    EntityMapper<StoreModel, Store> {

    /**
     * Map an instance of a [StoreModel] to a [Store] model
     */
    override fun mapFromRemote(type: StoreModel) =
        Store(
            type.storeId,
            type.address,
            type.city,
            type.name,
            type.latitude,
            type.zipCode,
            type.storeLogoUrl.trim(),
            type.phone,
            type.longitude,
            type.state
        )
}