
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

package com.sprouts.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;

import androidx.appcompat.widget.AppCompatImageView;


/**
 * A zoomable ImageView. See {@link PhotoViewAttacher} for most of the details on how the zooming
 * is accomplished
 */
@SuppressWarnings("unused")
public class PhotoView extends AppCompatImageView {

    private PhotoViewAttacher attacher;
    private ScaleType pendingScaleType;

    /**
     * Instantiates a new Photo view.
     *
     * @param context the context
     */
    public PhotoView(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Photo view.
     *
     * @param context the context
     * @param attr    the attr
     */
    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    /**
     * Instantiates a new Photo view.
     *
     * @param context  the context
     * @param attr     the attr
     * @param defStyle the def style
     */
    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init();
    }

    private void init() {
        attacher = new PhotoViewAttacher(this);
        //We always pose as a Matrix scale type, though we can change to another scale type
        //via the attacher
        super.setScaleType(ScaleType.MATRIX);
        //apply the previously applied scale type
        if (pendingScaleType != null) {
            setScaleType(pendingScaleType);
            pendingScaleType = null;
        }
    }

    /**
     * Get the current {@link PhotoViewAttacher} for this view. Be wary of holding on to references
     * to this attacher, as it has a reference to this view, which, if a reference is held in the
     * wrong place, can cause memory leaks.
     *
     * @return the attacher.
     */
    public PhotoViewAttacher getAttacher() {
        return attacher;
    }

    @Override
    public ScaleType getScaleType() {
        return attacher.getScaleType();
    }

    @Override
    public Matrix getImageMatrix() {
        return attacher.getImageMatrix();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        attacher.setOnLongClickListener(l);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        attacher.setOnClickListener(l);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (attacher == null) {
            pendingScaleType = scaleType;
        } else {
            attacher.setScaleType(scaleType);
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        // setImageBitmap calls through to this method
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        boolean changed = super.setFrame(l, t, r, b);
        if (changed) {
            attacher.update();
        }
        return changed;
    }

    /**
     * Sets rotation to.
     *
     * @param rotationDegree the rotation degree
     */
    public void setRotationTo(float rotationDegree) {
        attacher.setRotationTo(rotationDegree);
    }

    /**
     * Sets rotation by.
     *
     * @param rotationDegree the rotation degree
     */
    public void setRotationBy(float rotationDegree) {
        attacher.setRotationBy(rotationDegree);
    }

    /**
     * Is zoomable boolean.
     *
     * @return the boolean
     */
    public boolean isZoomable() {
        return attacher.isZoomable();
    }

    /**
     * Sets zoomable.
     *
     * @param zoomable the zoomable
     */
    public void setZoomable(boolean zoomable) {
        attacher.setZoomable(zoomable);
    }

    /**
     * Gets display rect.
     *
     * @return the display rect
     */
    public RectF getDisplayRect() {
        return attacher.getDisplayRect();
    }

    /**
     * Gets display matrix.
     *
     * @param matrix the matrix
     */
    public void getDisplayMatrix(Matrix matrix) {
        attacher.getDisplayMatrix(matrix);
    }

    /**
     * Sets display matrix.
     *
     * @param finalRectangle the final rectangle
     * @return the display matrix
     */
    @SuppressWarnings("UnusedReturnValue") public boolean setDisplayMatrix(Matrix finalRectangle) {
        return attacher.setDisplayMatrix(finalRectangle);
    }

    /**
     * Gets supp matrix.
     *
     * @param matrix the matrix
     */
    public void getSuppMatrix(Matrix matrix) {
        attacher.getSuppMatrix(matrix);
    }

    /**
     * Sets supp matrix.
     *
     * @param matrix the matrix
     * @return the supp matrix
     */
    public boolean setSuppMatrix(Matrix matrix) {
        return attacher.setDisplayMatrix(matrix);
    }

    /**
     * Gets minimum scale.
     *
     * @return the minimum scale
     */
    public float getMinimumScale() {
        return attacher.getMinimumScale();
    }

    /**
     * Gets medium scale.
     *
     * @return the medium scale
     */
    public float getMediumScale() {
        return attacher.getMediumScale();
    }

    /**
     * Gets maximum scale.
     *
     * @return the maximum scale
     */
    public float getMaximumScale() {
        return attacher.getMaximumScale();
    }

    /**
     * Gets scale.
     *
     * @return the scale
     */
    public float getScale() {
        return attacher.getScale();
    }

    /**
     * Sets allow parent intercept on edge.
     *
     * @param allow the allow
     */
    public void setAllowParentInterceptOnEdge(boolean allow) {
        attacher.setAllowParentInterceptOnEdge(allow);
    }

    /**
     * Sets minimum scale.
     *
     * @param minimumScale the minimum scale
     */
    public void setMinimumScale(float minimumScale) {
        attacher.setMinimumScale(minimumScale);
    }

    /**
     * Sets medium scale.
     *
     * @param mediumScale the medium scale
     */
    public void setMediumScale(float mediumScale) {
        attacher.setMediumScale(mediumScale);
    }

    /**
     * Sets maximum scale.
     *
     * @param maximumScale the maximum scale
     */
    public void setMaximumScale(float maximumScale) {
        attacher.setMaximumScale(maximumScale);
    }

    /**
     * Sets scale levels.
     *
     * @param minimumScale the minimum scale
     * @param mediumScale  the medium scale
     * @param maximumScale the maximum scale
     */
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        attacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    /**
     * Sets on matrix change listener.
     *
     * @param listener the listener
     */
    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        attacher.setOnMatrixChangeListener(listener);
    }

    /**
     * Sets on photo tap listener.
     *
     * @param listener the listener
     */
    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        attacher.setOnPhotoTapListener(listener);
    }

    /**
     * Sets on outside photo tap listener.
     *
     * @param listener the listener
     */
    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener listener) {
        attacher.setOnOutsidePhotoTapListener(listener);
    }

    /**
     * Sets on view tap listener.
     *
     * @param listener the listener
     */
    public void setOnViewTapListener(OnViewTapListener listener) {
        attacher.setOnViewTapListener(listener);
    }

    /**
     * Sets on view drag listener.
     *
     * @param listener the listener
     */
    public void setOnViewDragListener(OnViewDragListener listener) {
        attacher.setOnViewDragListener(listener);
    }

    /**
     * Sets scale.
     *
     * @param scale the scale
     */
    public void setScale(float scale) {
        attacher.setScale(scale);
    }

    /**
     * Sets scale.
     *
     * @param scale   the scale
     * @param animate the animate
     */
    public void setScale(float scale, boolean animate) {
        attacher.setScale(scale, animate);
    }

    /**
     * Sets scale.
     *
     * @param scale   the scale
     * @param focalX  the focal x
     * @param focalY  the focal y
     * @param animate the animate
     */
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        attacher.setScale(scale, focalX, focalY, animate);
    }

    /**
     * Sets zoom transition duration.
     *
     * @param milliseconds the milliseconds
     */
    public void setZoomTransitionDuration(int milliseconds) {
        attacher.setZoomTransitionDuration(milliseconds);
    }

    /**
     * Sets on double tap listener.
     *
     * @param onDoubleTapListener the on double tap listener
     */
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        attacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    /**
     * Sets on scale change listener.
     *
     * @param onScaleChangedListener the on scale changed listener
     */
    public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangedListener) {
        attacher.setOnScaleChangeListener(onScaleChangedListener);
    }

    /**
     * Sets on single fling listener.
     *
     * @param onSingleFlingListener the on single fling listener
     */
    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        attacher.setOnSingleFlingListener(onSingleFlingListener);
    }
}
