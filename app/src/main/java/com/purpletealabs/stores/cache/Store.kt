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

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Model used solely for the caching of an store
 */
@Entity(tableName = "stores", indices = [Index(value = ["storeId"], unique = true)])
data class Store(
    @PrimaryKey
    val storeId: Int,
    val address: String,
    val city: String,
    val name: String,
    val latitude: Float,
    val zipCode: Int,
    val storeLogoUrl: String,
    val phone: String,
    val longitude: Float,
    val state: String
)