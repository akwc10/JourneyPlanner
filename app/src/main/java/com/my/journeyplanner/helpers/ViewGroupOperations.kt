package com.my.journeyplanner.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.my.journeyplanner.R

fun ViewGroup.addTwoColumnRow(
    rowIndex: Int,
    layoutResourceId: Int = R.layout.two_columns_row,
    columnZeroText: String = "",
    columnOneText: String = ""
) {
    val viewGroupToAdd =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(layoutResourceId, null)
    viewGroupToAdd.apply {
        findViewById<TextView>(R.id.textViewColumnZero).text = columnZeroText
        findViewById<TextView>(R.id.textViewColumnOne).text = columnOneText
    }
    this.addView(
        viewGroupToAdd, rowIndex, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )
}