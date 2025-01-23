package com.sprouts.photoview.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Image in recyclerview
 */
class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private fun bind(title: String) {
    }

    companion object {
        @JvmStatic
        fun inflate(parent: ViewGroup): ImageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            return ImageViewHolder(view)
        }
    }
}
