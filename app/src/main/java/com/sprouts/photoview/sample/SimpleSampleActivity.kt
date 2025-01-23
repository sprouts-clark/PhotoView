package com.sprouts.photoview.sample

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.sprouts.photoview.OnMatrixChangedListener
import com.sprouts.photoview.OnPhotoTapListener
import com.sprouts.photoview.OnSingleFlingListener
import com.sprouts.photoview.PhotoView
import java.util.Random

class SimpleSampleActivity : AppCompatActivity() {
    private var mPhotoView: PhotoView? = null
    private var mCurrMatrixTv: TextView? = null

    private var mCurrentToast: Toast? = null

    private var mCurrentDisplayMatrix: Matrix? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_sample)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Simple Sample"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_zoom_toggle -> {
                    mPhotoView!!.isZoomable = !mPhotoView!!.isZoomable
                    item.setTitle(if (mPhotoView!!.isZoomable) R.string.menu_zoom_disable else R.string.menu_zoom_enable)
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_fit_center -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.CENTER
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_fit_start -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.FIT_START
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_fit_end -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.FIT_END
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_fit_xy -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.FIT_XY
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_scale_center -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.CENTER
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_scale_center_crop -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_scale_center_inside -> {
                    mPhotoView!!.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    return@OnMenuItemClickListener true
                }

                R.id.menu_scale_random_animate, R.id.menu_scale_random -> {
                    val r = Random()

                    val minScale = mPhotoView!!.minimumScale
                    val maxScale = mPhotoView!!.maximumScale
                    val randomScale = minScale + (r.nextFloat() * (maxScale - minScale))
                    mPhotoView!!.setScale(randomScale, item.itemId == R.id.menu_scale_random_animate)

                    showToast(String.format(SCALE_TOAST_STRING, randomScale))

                    return@OnMenuItemClickListener true
                }

                R.id.menu_matrix_restore -> {
                    if (mCurrentDisplayMatrix == null) showToast("You need to capture display matrix first")
                    else mPhotoView!!.setDisplayMatrix(mCurrentDisplayMatrix)
                    return@OnMenuItemClickListener true
                }

                R.id.menu_matrix_capture -> {
                    mCurrentDisplayMatrix = Matrix()
                    mPhotoView!!.getDisplayMatrix(mCurrentDisplayMatrix)
                    return@OnMenuItemClickListener true
                }
            }
            false
        })
        mPhotoView = findViewById(R.id.iv_photo)
        mCurrMatrixTv = findViewById(R.id.tv_current_matrix)

        val bitmap = ContextCompat.getDrawable(this, R.drawable.wallpaper)
        mPhotoView?.setImageDrawable(bitmap)

        // Lets attach some listeners, not required though!
        mPhotoView?.setOnMatrixChangeListener(MatrixChangeListener())
        mPhotoView?.setOnPhotoTapListener(PhotoTapListener())
        mPhotoView?.setOnSingleFlingListener(SingleFlingListener())
    }

    private inner class PhotoTapListener : OnPhotoTapListener {
        override fun onPhotoTap(view: ImageView?, x: Float, y: Float) {
            val xPercentage = x * 100f
            val yPercentage = y * 100f

            showToast(String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage, view?.id ?: 0))
        }
    }

    private fun showToast(text: CharSequence) {
        if (mCurrentToast != null) {
            mCurrentToast!!.cancel()
        }

        mCurrentToast = Toast.makeText(this@SimpleSampleActivity, text, Toast.LENGTH_SHORT)
        mCurrentToast?.show()
    }

    private inner class MatrixChangeListener : OnMatrixChangedListener {
        override fun onMatrixChanged(rect: RectF) {
            mCurrMatrixTv!!.text = rect.toString()
        }
    }

    private inner class SingleFlingListener : OnSingleFlingListener {
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            Log.d("PhotoView", String.format(FLING_LOG_STRING, velocityX, velocityY))
            return true
        }
    }

    companion object {
        const val PHOTO_TAP_TOAST_STRING: String = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d"
        const val SCALE_TOAST_STRING: String = "Scaled to: %.2ff"
        const val FLING_LOG_STRING: String = "Fling velocityX: %.2f, velocityY: %.2f"
    }
}
