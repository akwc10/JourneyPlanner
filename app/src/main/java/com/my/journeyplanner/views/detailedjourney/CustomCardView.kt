package com.my.journeyplanner.views.detailedjourney

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.my.journeyplanner.R
import kotlinx.android.synthetic.main.card_view_journey.view.*

class CustomCardView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {
    init {
        View.inflate(context, R.layout.card_view_journey, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView)
        textViewStartAndArrivalTime.text =
            attributes.getString(R.styleable.CustomCardView_startAndArrivalTime)
        textViewDuration.text = attributes.getString(R.styleable.CustomCardView_duration)
        attributes.recycle()
    }
}
