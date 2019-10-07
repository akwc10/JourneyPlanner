package com.my.journeyplanner.views.itineraryresults

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.journeyplanner.R
import com.my.presenter.itineraryresults.ItineraryResultsContract

class ItineraryResultsActivity : AppCompatActivity(), ItineraryResultsContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerary_results)
    }

    override fun updateItineraryResultsListView(itineraryResults: List<Any>) {

    }

    override fun showDetailedActivity(detailedItineraryResults: List<Any>) {

    }

    override fun showMapActivity(mapItineraryResults: List<Any>) {

    }

    override fun showSaveJourneyActivity(journeyDetails: List<Any>) {

    }
}
