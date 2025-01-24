
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

import android.widget.ImageView;


/**
 * The interface On outside photo tap listener.
 */
public interface OnOutsidePhotoTapListener {


    /**
     * On outside photo tap.
     *
     * @param imageView the image view
     */
    void onOutsidePhotoTap(ImageView imageView);
}
