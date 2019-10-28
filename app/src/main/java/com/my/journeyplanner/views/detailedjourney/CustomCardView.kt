package com.my.journeyplanner.views.detailedjourney

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.my.journeyplanner.R

class CustomCardView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {
    init {
        View.inflate(context, R.layout.card_view_journey, this)
        val startAndArrivalTime = findViewById<TextView>(R.id.textViewStartAndArrivalTime)
        val duration = findViewById<TextView>(R.id.textViewDuration)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView)
        startAndArrivalTime.text =
            attributes.getString(R.styleable.CustomCardView_startAndArrivalTime)
        duration.text = attributes.getText(R.styleable.CustomCardView_duration)
        attributes.recycle()
    }
}
