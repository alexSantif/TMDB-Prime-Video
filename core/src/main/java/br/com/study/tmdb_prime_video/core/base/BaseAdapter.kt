package br.com.study.tmdb_prime_video.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T: BaseViewHolder<U>, U>(private val viewHolderLauncher: (ViewGroup) -> T) : RecyclerView.Adapter<T>() {

    var items: MutableList<U> = mutableListOf()

    lateinit var onClick: (View.OnClickListener) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderLauncher(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position])
        holder.onItemClick(onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

abstract class BaseViewHolder<U>(layout: Int, viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
) {

    abstract fun bind(item: U)

    abstract fun onItemClick(onClick: (OnClickListener) -> Unit)
}