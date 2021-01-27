package com.example.search.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.search.R
import com.kotlin.project.data.model.TheMovieDBStatus

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.VISIBLE
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(status: TheMovieDBStatus?) {
    status ?: return
    isRefreshing = status == TheMovieDBStatus.ReLoading
}

@BindingAdapter("imageFromUrl", requireAll = false)
fun bindImageFromUrlWithPlaceholder(
    view: ImageView,
    imageFromUrl: String
) {
    GlideApp.with(view.context)
        .load(imageFromUrl)
        .downsample(DownsampleStrategy.CENTER_INSIDE)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
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
        .placeholder(R.color.grey1)
        .fallback(R.color.grey1)
        .into(view)
}
