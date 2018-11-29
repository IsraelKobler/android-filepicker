/*
 * Copyright (C) 2018 Angad Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.angads25.filepicker.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.github.angads25.filepicker.interfaces.OnItemClickListener
import com.github.angads25.filepicker.interfaces.OnItemLongClickListener

/**
 *
 *
 * Created by Angad Singh on 30/11/18.
 *
 */
class RecyclerViewTouchHandler (context: Context, private val mRecyclerView: RecyclerView) : RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetector

    private var clickListener: OnItemClickListener? = null
    private var mOnlongClickListener: OnItemLongClickListener? = null

    init {
        mRecyclerView.addOnItemTouchListener(this)

        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = mRecyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && mOnlongClickListener != null) {
                    mOnlongClickListener!!.onLongClick(mRecyclerView, child, mRecyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child = rv.findChildViewUnder(e.x, e.y)
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener!!.onClick(rv, child, rv.getChildAdapterPosition(child))
            return true
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    fun setOnClickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    fun setOnlongClickListener(onlongClickListener: OnItemLongClickListener) {
        this.mOnlongClickListener = onlongClickListener
    }

}
