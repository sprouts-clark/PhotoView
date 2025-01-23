package com.sprouts.photoview.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sprouts.photoview.PhotoView
import com.squareup.picasso.Picasso

class PicassoSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        val photoView = findViewById<PhotoView>(R.id.iv_photo)

        Picasso.with(this)
            .load("https://pic4.zhimg.com/v2-4d9e9f936b9968f53be22b594aafa74f_r.jpg")
            .into(photoView)
    }
}
