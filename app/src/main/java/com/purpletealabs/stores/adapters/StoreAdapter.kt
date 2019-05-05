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

package com.purpletealabs.stores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.purpletealabs.stores.StoreListFragment
import com.purpletealabs.stores.StoreListFragmentDirections
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.databinding.ListItemStoreBinding
import com.purpletealabs.stores.extension.Intents

/**
 * Adapter for the [RecyclerView] in [StoreListFragment].
 */
class StoreAdapter : PagedListAdapter<Store, StoreAdapter.ViewHolder>(StoreDiffCallback()),
    StoreAdapterCommunication {

    init {
        setHasStableIds(true)
    }

    override fun navigateToStoreDetail(view: View, model: Store) {
        val actionBrowseFragmentToDetailsFragment =
            StoreListFragmentDirections.actionStoreListFragmentToStoreDetailFragment(
                model,
                model.name
            )
        view.findNavController().navigate(actionBrowseFragmentToDetailsFragment)
    }

    override fun locateStoreInGoogleMaps(view: View, model: Store) {
        view.context.startActivity(
            Intents.googleMapsIntent(
                model.latitude,
                model.longitude
            )
        )
    }

    override fun callStore(view: View, model: Store) {
        view.context.startActivity(
            Intents.callIntent(
                model.phone
            )
        )
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = getItem(position)
        store?.let { storeNotNull ->
            holder.apply {
                bind(this@StoreAdapter, storeNotNull)
                itemView.tag = storeNotNull
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemStoreBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class ViewHolder(
        private val binding: ListItemStoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: StoreAdapterCommunication, item: Store) {
            binding.apply {
                communication = listener
                store = item
                executePendingBindings()
            }
        }
    }
}

private class StoreDiffCallback : DiffUtil.ItemCallback<Store>() {

    override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
        return oldItem.storeId == newItem.storeId
    }

    override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
        return oldItem == newItem
    }
}