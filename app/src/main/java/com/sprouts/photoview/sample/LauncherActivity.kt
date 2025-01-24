/*
 *
 *  Copyright 2025 sprouts Clark.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.sprouts.photoview.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.app_name)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter()
    }


    private class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val holder = ItemViewHolder.newInstance(parent)
            holder.itemView.setOnClickListener { v: View? ->

                val clazz: Class<*> = when (holder.adapterPosition) {
                    0 -> SimpleSampleActivity::class.java
                    1 -> ViewPagerActivity::class.java
                    2 -> RotationSampleActivity::class.java
                    3 -> PicassoSampleActivity::class.java
                    4 -> CoilSampleActivity::class.java
                    5 -> ActivityTransitionActivity::class.java
                    6 -> ImmersiveActivity::class.java
                    else -> SimpleSampleActivity::class.java
                }
                val context = holder.itemView.context
                context.startActivity(Intent(context, clazz))
            }
            return holder
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(options[position])
        }

        override fun getItemCount(): Int {
            return options.size
        }
    }

    private class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTextTitle: TextView = view.findViewById(R.id.title)

        fun bind(title: String?) {
            mTextTitle.text = title
        }

        companion object {
            fun newInstance(parent: ViewGroup): ItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sample, parent, false)
                return ItemViewHolder(view)
            }
        }
    }

    companion object {
        val options: Array<String> = arrayOf(
            "Simple Sample",
            "ViewPager Sample",
            "Rotation Sample",
            "Picasso Sample",
            "Coil Sample",
            "Activity Transition Sample",
            "Immersive Sample"
        )
    }
}
