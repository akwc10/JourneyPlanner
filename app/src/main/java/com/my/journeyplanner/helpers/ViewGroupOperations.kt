package com.my.journeyplanner.helpers

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.my.journeyplanner.R

private const val COLUMN_ZERO_DP = 60.0f

fun ViewGroup.addTwoColumnRow(
    rowIndex: Int,
    columnZeroText: String,
    columnOneText: String,
    textStyle: Typeface = Typeface.DEFAULT
) {
    val viewGroupToAdd =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.two_columns_row, null)
    viewGroupToAdd.findViewById<TextView>(R.id.textViewColumnZero).apply {
        typeface = textStyle
        width = (COLUMN_ZERO_DP * context.resources.displayMetrics.density + 0.5f).toInt()
        text = columnZeroText
    }
    viewGroupToAdd.findViewById<TextView>(R.id.textViewColumnOne).apply {
        typeface = textStyle
        text = columnOneText
    }
    this.addView(
        viewGroupToAdd, rowIndex, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )
}