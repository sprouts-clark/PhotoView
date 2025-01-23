package com.sprouts.photoview.sample

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sprouts.photoview.sample.ImageViewHolder.Companion.inflate

/**
 * Image adapter
 */
class ImageAdapter(var mListener: Listener) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val holder = inflate(parent)
        holder.itemView.setOnClickListener { view -> mListener.onImageClicked(view) }
        return holder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }

    interface Listener {
        fun onImageClicked(view: View?)
    }
}
