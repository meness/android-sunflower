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

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.purpletealabs.stores.R
import com.purpletealabs.stores.extension.setPrefetchText

@BindingAdapter(
    "app:asyncText",
    "android:textSize",
    requireAll = false
)
fun asyncText(view: TextView, text: CharSequence, textSize: Int?) {
    // first, set all measurement affecting properties of the text
    // (size, locale, typeface, direction, etc)
    if (textSize != null) {
        // interpret the text size as SP
        view.textSize = textSize.toFloat()
    }
    (view as AppCompatTextView).setPrefetchText(text)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.progress_animation)
            .into(view)
    }
}
