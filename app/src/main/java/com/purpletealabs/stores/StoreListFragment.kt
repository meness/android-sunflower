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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.purpletealabs.stores.adapters.StoreAdapter
import com.purpletealabs.stores.databinding.FragmentStoreListBinding
import com.purpletealabs.stores.utilities.InjectorUtils
import com.purpletealabs.stores.presentation.StoreListViewModel

class StoreListFragment : Fragment() {

    private lateinit var viewModel: StoreListViewModel
    private lateinit var binding: FragmentStoreListBinding
    private lateinit var storeAdapter: StoreAdapter

    private fun showErrorState() {
        findNavController().navigate(StoreListFragmentDirections.actionStoreListFragmentToFailureFragment())
    }

    private fun hideErrorState() {
    }

    private fun setupBrowseRecycler() {
        binding.recyclerBrowse.run {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            adapter = storeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Prevent memory leaks
        binding.recyclerBrowse.adapter = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreListBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        provideViewModels(context)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStores()
        observeLoadingStoresIssue()
        setupBrowseRecycler()
    }

    private fun provideViewModels(context: Context) {
        val factory = InjectorUtils.provideStoreListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(StoreListViewModel::class.java)
    }

    private fun observeLoadingStoresIssue() {
        // TODO: Implement loading issues
        /*viewModel.hasLoadingIssue().observe(this, Observer {
            when (it) {
                true -> {
                    showErrorState()
                }
                false -> hideErrorState()
            }
        })*/
    }

    private fun observeStores() {
        viewModel.stores.observe(viewLifecycleOwner, Observer { stores ->
            if (stores != null) storeAdapter.submitList(stores)
        })
    }
}