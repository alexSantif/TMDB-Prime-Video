package br.com.study.tmdb_prime_video.home.presentation.adapter

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import br.com.study.tmdb_prime_video.core.BuildConfig
import br.com.study.tmdb_prime_video.core.base.BaseViewHolder
import br.com.study.tmdb_prime_video.home.R
import br.com.study.tmdb_prime_video.home.data.model.MovieData
import com.bumptech.glide.Glide

class MovieCoverAdapter(viewGroup: ViewGroup) :
    BaseViewHolder<MovieData>(R.layout.movie_item_view, viewGroup) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(item: MovieData) {

        Glide
            .with(itemView.context)
            .load("${BuildConfig.IMAGES_BASE_URL}${item.posterPath}")
            .centerCrop()
            .into(itemView.findViewById(R.id.iv_movie_cover))
    }

    override fun onItemClick(onClick: (View.OnClickListener) -> Unit) {
        itemView.setOnClickListener {
            onClick {}
        }
    }
}