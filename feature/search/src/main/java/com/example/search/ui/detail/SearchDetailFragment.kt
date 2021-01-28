package com.example.search.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.search.BuildConfig
import com.example.search.R
import com.example.search.R.transition
import com.example.search.databinding.FragmentSearchDetailBinding
import javax.inject.Inject

class SearchDetailFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentSearchDetailBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val searchDetailViewModel: SearchDetailViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailBinding.inflate(inflater, container, false)
        setSharedElementTransitionOnEnter()
        postponeEnterTransition()
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.mainImage.apply {
                transitionName = SearchDetailFragmentArgs.fromBundle(it).transitionName
                startEnterTransitionAfterLoadingImage(
                    SearchDetailFragmentArgs.fromBundle(it).imgUrl,
                    this
                )
            }
            val mId = SearchDetailFragmentArgs.fromBundle(it).movieId
            searchDetailViewModel.setMovieId(mId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun observe() {
        searchDetailViewModel.detailData.observe(viewLifecycleOwner) {
            binding.detailData = it
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(transition.move)
    }

    private fun startEnterTransitionAfterLoadingImage(imageAddress: String, imageView: ImageView) {
        Glide.with(this)
            .load(BuildConfig.IMGURL + imageAddress)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .placeholder(R.color.grey1)
            .fallback(R.color.grey1)
            .into(imageView)
    }
}
