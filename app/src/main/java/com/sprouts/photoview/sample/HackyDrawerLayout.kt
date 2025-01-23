package com.sprouts.photoview.sample

import android.content.Context
import android.view.MotionEvent
import androidx.drawerlayout.widget.DrawerLayout


class HackyDrawerLayout(context: Context) : DrawerLayout(context) {
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return false
        }
    }
}
