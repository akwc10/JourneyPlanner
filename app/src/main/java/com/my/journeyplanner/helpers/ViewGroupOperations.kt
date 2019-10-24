package com.my.journeyplanner.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.my.journeyplanner.R

fun ViewGroup.addLegRow(rowIndex: Int, modeOrPipe: String, durationOrInstructionSummary: String) {
    val viewGroupToAdd =
        (this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.leg_row, null)
    val textViewModeOrPipe = viewGroupToAdd.findViewById<TextView>(R.id.textViewModeOrPipe)
    val textViewDurationOrInstructionSummary =
        viewGroupToAdd.findViewById<TextView>(R.id.textViewDurationOrInstructionSummary)
    textViewModeOrPipe.text = modeOrPipe
    textViewDurationOrInstructionSummary.text = durationOrInstructionSummary
    this.addView(
        viewGroupToAdd, rowIndex, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )
}