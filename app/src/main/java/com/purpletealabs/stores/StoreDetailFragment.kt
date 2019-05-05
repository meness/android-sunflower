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

package com.purpletealabs.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.purpletealabs.stores.adapters.StoreAdapterCommunication
import com.purpletealabs.stores.cache.Store
import com.purpletealabs.stores.databinding.FragmentStoreDetailBinding
import com.purpletealabs.stores.extension.Intents

/**
 * A fragment representing a single Plant detail screen.
 */
class StoreDetailFragment : Fragment(), StoreAdapterCommunication {

    private lateinit var binding: FragmentStoreDetailBinding
    private val args: StoreDetailFragmentArgs by navArgs()

    override fun navigateToStoreDetail(view: View, model: Store) {
        // We do not navigate here again
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentStoreDetailBinding>(
            inflater,
            R.layout.fragment_store_detail, container, false
        ).apply {
            store = args.Store
            communication = this@StoreDetailFragment
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_store_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_call -> {
                callStore(binding.root, args.Store)
                true
            }
            R.id.action_show_on_map -> {
                locateStoreInGoogleMaps(binding.root, args.Store)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
