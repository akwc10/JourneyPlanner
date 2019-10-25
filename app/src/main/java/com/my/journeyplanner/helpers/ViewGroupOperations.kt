package com.my.journeyplanner.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.my.journeyplanner.R

private const val MODE_OR_EMPTY_DPS = 60

fun ViewGroup.addLegRow(rowIndex: Int, modeOrEmpty: String, durationOrInstructionSummary: String) {
    val viewGroupToAdd =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.leg_row, null)
    viewGroupToAdd.findViewById<TextView>(R.id.textViewModeOrEmpty).apply {
        width = MODE_OR_EMPTY_DPS * context.resources.displayMetrics.density.toInt() + 0.5.toInt()
        text = modeOrEmpty
    }
    viewGroupToAdd.findViewById<TextView>(R.id.textViewDurationOrInstructionSummary).text =
        durationOrInstructionSummary
    this.addView(
        viewGroupToAdd, rowIndex, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )
}