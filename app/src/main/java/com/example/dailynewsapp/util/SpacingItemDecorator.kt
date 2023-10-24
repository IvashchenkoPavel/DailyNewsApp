package com.example.dailynewsapp.util

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecorator(val verticalSpace: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = verticalSpace
        outRect.top = verticalSpace
    }
}