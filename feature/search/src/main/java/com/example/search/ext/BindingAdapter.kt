package com.example.search.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.search.R

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.VISIBLE
}

@BindingAdapter("imageFromUrl", requireAll = false)
fun bindImageFromUrlWithPlaceholder(
    view: ImageView,
    imageFromUrl: String
) {
    if (imageFromUrl.isEmpty()) {
        view.setImageResource(R.color.grey)
    } else {
        GlideApp.with(view.context)
            .load(imageFromUrl)
            .downsample(DownsampleStrategy.CENTER_INSIDE)
            .dontTransform()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    p0: GlideException?,
                    p1: Any?,
                    target: Target<Drawable>?,
                    p3: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    p0: Drawable?,
                    p1: Any?,
                    target: Target<Drawable>?,
                    p3: DataSource?,
                    p4: Boolean
                ): Boolean {
                    return false
                }
            })
            .placeholder(R.color.grey)
            .fallback(R.color.grey)
            .into(view)
    }
}
