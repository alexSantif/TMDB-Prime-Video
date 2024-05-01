package br.com.study.tmdb_prime_video.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.study.tmdb_prime_video.home.R
import br.com.study.tmdb_prime_video.home.databinding.ImageSliderItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FeaturedMoviesAdapter(private val imageUrlList: MutableList<String>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<FeaturedMoviesAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {

            Glide.with(binding.root.context)
                .load(imageUrl)
//                .error(R.drawable.ic_baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivImage)
        }

    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ImageSliderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(imageUrlList[position])

        if (position == imageUrlList.size - 2) {
            viewPager2.post(runnable)
        }
    }

    private val runnable = Runnable {
        imageUrlList.addAll(imageUrlList)
        notifyDataSetChanged()
    }
}