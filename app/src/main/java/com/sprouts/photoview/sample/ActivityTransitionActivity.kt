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
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityTransitionActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = GridLayoutManager(this, 2)
        val imageAdapter = ImageAdapter(object : ImageAdapter.Listener {
            override fun onImageClicked(view: View?) {
                transition(view!!)
            }
        })
        list.adapter = imageAdapter
    }

    private fun transition(view: View) {
        val intent = Intent(this@ActivityTransitionActivity, ActivityTransitionToActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ActivityTransitionActivity, view, getString(R.string.transition_test))
        startActivity(intent, options.toBundle())
    }
}
