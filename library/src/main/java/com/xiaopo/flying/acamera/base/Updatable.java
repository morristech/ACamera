/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiaopo.flying.acamera.base;

public interface Updatable<T> {
  /**
   * Implementations MUST ALWAYS satisfy the following constraints:
   * <ul>
   * <li>Return quickly, without performing any expensive work.</li>
   * <li>Execute in a predictable, stable, amount of time.</li>
   * <li>Never block.</li>
   * <li>Be thread-safe.</li>
   * <li>Never leak control of the thread on which they are invoked. (e.g.
   * invoke callbacks which may violate the above constraints)</li>
   * </ul>
   */
  void update(T t);
}
