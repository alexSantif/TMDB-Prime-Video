package br.com.study.tmdb_prime_video.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.study.tmdb_prime_video.core.BuildConfig
import br.com.study.tmdb_prime_video.home.databinding.ImageSliderItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FeaturedMoviesAdapter(private val imageUrlList: MutableList<String?>?) :
    RecyclerView.Adapter<FeaturedMoviesAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {

            Glide.with(binding.root.context)
                .load("${BuildConfig.IMAGES_BASE_URL}$imageUrl")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivImage)
        }

    }

    override fun getItemCount(): Int = imageUrlList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ImageSliderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        imageUrlList?.get(position)?.let { holder.setData(it) }
    }

    companion object {

        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/original"
    }
}