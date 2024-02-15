package com.example.homework_22.presentation.adapters.main.custom_layout

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
        requestLayout()
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {

        detachAndScrapAttachedViews(recycler)

        val totalWidth = width
        var totalHeight = height

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)

            when {
                itemCount == 1 -> layoutDecoratedWithLog(view, 0, 0, totalWidth, totalHeight)
                itemCount == 2 && i == 0 -> layoutDecoratedWithLog(view, 0, 0, totalWidth / 2, totalHeight)
                itemCount == 2 && i == 1 -> layoutDecoratedWithLog(view, totalWidth / 2, 0, totalWidth, totalHeight)
                itemCount >= 3 && i == 0 -> layoutDecoratedWithLog(view, 0, 0, totalWidth / 2, totalHeight)
                itemCount >= 3 && i == 1 -> layoutDecoratedWithLog(view, totalWidth / 2, 0, totalWidth, totalHeight / 2)
                itemCount >= 3 && i == 2 -> layoutDecoratedWithLog(view, totalWidth / 2, totalHeight / 2, totalWidth, totalHeight)
            }
        }
    }

    private fun layoutDecoratedWithLog(child: View, left: Int, top: Int, right: Int, bottom: Int) {
        layoutDecorated(child, left, top, right, bottom)
    }
}