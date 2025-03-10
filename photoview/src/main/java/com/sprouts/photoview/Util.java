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

import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * The type Util.
 */
class Util {

    /**
     * Check zoom levels.
     *
     * @param minZoom the min zoom
     * @param midZoom the mid zoom
     * @param maxZoom the max zoom
     */
    static void checkZoomLevels(float minZoom, float midZoom,
                                float maxZoom) {
        if (minZoom >= midZoom) {
            throw new IllegalArgumentException(
                    "Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (midZoom >= maxZoom) {
            throw new IllegalArgumentException(
                    "Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    /**
     * Has drawable boolean.
     *
     * @param imageView the image view
     * @return the boolean
     */
    static boolean hasDrawable(ImageView imageView) {
        return imageView.getDrawable() != null;
    }

    /**
     * Is supported scale type boolean.
     *
     * @param scaleType the scale type
     * @return the boolean
     */
    static boolean isSupportedScaleType(final ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (scaleType) {
            case MATRIX:
                throw new IllegalStateException("Matrix scale type is not supported");
        }
        return true;
    }

    /**
     * Gets pointer index.
     *
     * @param action the action
     * @return the pointer index
     */
    static int getPointerIndex(int action) {
        return (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
    }
}
