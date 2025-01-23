package com.sprouts.photoview.sample

import android.R.attr
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sprouts.photoview.PhotoView
import com.squareup.picasso.Picasso

/**
 * Shows immersive image viewer
 */
class ImmersiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersive)

        val photoView = findViewById<PhotoView>(R.id.photo_view)
        Picasso.with(this)
            .load("https://pic4.zhimg.com/v2-4d9e9f936b9968f53be22b594aafa74f_r.jpg")
            .into(photoView)
        photoView.setOnPhotoTapListener { view, x, y ->
            fullScreen();
        }
    }

    fun fullScreen() {
        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.

        val uiOptions = window.decorView.systemUiVisibility
        var newUiOptions = uiOptions
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        val isImmersiveModeEnabled = isImmersiveModeEnabled
        if (isImmersiveModeEnabled) {
            Log.i("TEST", "Turning immersive mode mode off. ")
        } else {
            Log.i("TEST", "Turning immersive mode mode on.")
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        window.decorView.systemUiVisibility = newUiOptions
        //END_INCLUDE (set_ui_flags)
    }

    private val isImmersiveModeEnabled: Boolean
        get() = ((attr.uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == attr.uiOptions)
}
