package com.my.journeyplanner.views.itineraryresultslegs

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.journeyplanner.R
import com.my.journeyplanner.views.results.EXTRA_ITINERARY_RESULTS_LEGS
import com.my.presenter.itineraryresultslegs.ItineraryResultsLegsContract

class ItineraryResultsLegsActivity : AppCompatActivity(), ItineraryResultsLegsContract.View {

    private val textViewResults by lazy { findViewById<TextView>(R.id.textViewResult) }

    //    TODO("Unable to cast")
    private val itineraryResultsLegs by lazy {
        intent.getSerializableExtra(
            EXTRA_ITINERARY_RESULTS_LEGS
        ) as List<JourneyPlannerResultDomainModel.Itinerary.Journey.Leg>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerary_results_legs)
        showResults()
    }

    override fun showResults() {
        textViewResults.text = itineraryResultsLegs.toString()
    }
}
