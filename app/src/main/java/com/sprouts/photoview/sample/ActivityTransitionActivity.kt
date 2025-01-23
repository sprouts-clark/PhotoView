package com.sprouts.photoview.sample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
