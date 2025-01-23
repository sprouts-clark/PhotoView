package com.sprouts.photoview.sample

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sprouts.photoview.PhotoView

class ViewPagerActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = SamplePagerAdapter()
    }

    internal class SamplePagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return sDrawables.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            val photoView = PhotoView(container.context)
            photoView.setImageResource(sDrawables[position])
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            return photoView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        companion object {
            private val sDrawables = intArrayOf(
                R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper,
                R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper
            )
        }
    }
}
