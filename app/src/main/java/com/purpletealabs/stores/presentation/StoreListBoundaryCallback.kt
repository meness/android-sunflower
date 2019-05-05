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

package com.github.meness.asteroids.presentation.asteroids

import androidx.paging.PagedList
import com.github.meness.asteroids.domain.interactor.param.GetAsteroidsParams
import com.github.meness.asteroids.domain.interactor.usecase.GetAsteroidsFromNetwork
import com.github.meness.asteroids.presentation.extension.plusDays
import com.github.meness.asteroids.presentation.model.AsteroidView
import io.reactivex.observers.DisposableCompletableObserver
import java.util.Date
import javax.inject.Inject

class StoreListBoundaryCallback @Inject constructor(
    private val asteroidsUseCase: GetAsteroidsFromNetwork
) :
    PagedList.BoundaryCallback<AsteroidView>() {
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()

        val startDate = Date()
        val endDate = startDate.plusDays(7)

        asteroidsUseCase.execute(AsteroidSubscriber(), GetAsteroidsParams(startDate, endDate))
    }

    fun dispose() {
        asteroidsUseCase.dispose()
    }

    override fun onItemAtEndLoaded(itemAtEnd: AsteroidView) {
        super.onItemAtEndLoaded(itemAtEnd)

        val startDate = itemAtEnd.closeApproachDate
        val endDate = startDate.plusDays(7)

        asteroidsUseCase.execute(AsteroidSubscriber(), GetAsteroidsParams(startDate, endDate))
    }

    inner class AsteroidSubscriber : DisposableCompletableObserver() {
        override fun onError(e: Throwable) {
            // TODO handle the network state here.
        }

        override fun onComplete() {
            // ignored because of single source of truth principle.
        }
    }
}