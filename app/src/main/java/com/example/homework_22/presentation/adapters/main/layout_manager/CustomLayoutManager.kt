package com.example.homework_22.presentation.adapters.main.layout_manager

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        val totalItemCount = itemCount
        return when (totalItemCount) {
            1 -> RecyclerView.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            2 -> RecyclerView.LayoutParams(MATCH_PARENT, MATCH_PARENT / 2)
            3 -> {
                if (orientation == HORIZONTAL) {
                    RecyclerView.LayoutParams(MATCH_PARENT / 2, MATCH_PARENT)
                } else {
                    RecyclerView.LayoutParams(MATCH_PARENT, MATCH_PARENT / 2)
                }
            }
            else -> RecyclerView.LayoutParams(MATCH_PARENT / 2, MATCH_PARENT / 2)
        }
    }
}