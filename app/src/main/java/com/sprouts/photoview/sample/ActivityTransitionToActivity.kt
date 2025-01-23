package com.sprouts.photoview.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity that gets transitioned to
 */
class ActivityTransitionToActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_to)
    }
}
