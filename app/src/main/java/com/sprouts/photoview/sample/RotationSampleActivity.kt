package com.sprouts.photoview.sample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.sprouts.photoview.PhotoView

class RotationSampleActivity : AppCompatActivity() {
    private var photo: PhotoView? = null
    private val handler = Handler()
    private var rotating = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation_sample)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.rotation)
        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_rotate_10_right -> {
                    photo!!.setRotationBy(10f)
                    return@OnMenuItemClickListener true
                }

                R.id.action_rotate_10_left -> {
                    photo!!.setRotationBy(-10f)
                    return@OnMenuItemClickListener true
                }

                R.id.action_toggle_automatic_rotation -> {
                    toggleRotation()
                    return@OnMenuItemClickListener true
                }

                R.id.action_reset_to_0 -> {
                    photo!!.setRotationTo(0f)
                    return@OnMenuItemClickListener true
                }

                R.id.action_reset_to_90 -> {
                    photo!!.setRotationTo(90f)
                    return@OnMenuItemClickListener true
                }

                R.id.action_reset_to_180 -> {
                    photo!!.setRotationTo(180f)
                    return@OnMenuItemClickListener true
                }

                R.id.action_reset_to_270 -> {
                    photo!!.setRotationTo(270f)
                    return@OnMenuItemClickListener true
                }
            }
            false
        })
        photo = findViewById(R.id.iv_photo)
        photo?.setImageResource(R.drawable.wallpaper)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

    private fun toggleRotation() {
        if (rotating) {
            handler.removeCallbacksAndMessages(null)
        } else {
            rotateLoop()
        }
        rotating = !rotating
    }

    private fun rotateLoop() {
        handler.postDelayed({
            photo!!.setRotationBy(1f)
            rotateLoop()
        }, 15)
    }
}
