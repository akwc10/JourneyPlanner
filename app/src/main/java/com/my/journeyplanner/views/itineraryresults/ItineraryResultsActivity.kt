package com.my.journeyplanner.views.itineraryresults

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.EXTRA_JOURNEY_PLANNER_RESULT
import com.my.journeyplanner.R
import com.my.journeyplanner.views.itineraryresultslegs.ItineraryResultsLegsActivity
import com.my.presenter.itineraryresults.ItineraryResultsContract
import com.my.presenter.itineraryresults.ItineraryResultsPresenter
import mu.KotlinLogging
import java.io.Serializable

const val EXTRA_ITINERARY_RESULTS_LEGS = "com.my.journeyplanner.ITINERARY_RESULTS_LEGS"

private val logger = KotlinLogging.logger {}

class ItineraryResultsActivity : AppCompatActivity(), ItineraryResultsContract.View {

    private val itineraryResultsPresenter by lazy { ItineraryResultsPresenter(this) }

    private val buttonShowMap by lazy { findViewById<Button>(R.id.buttonShowMap) }
    private val buttonSave by lazy { findViewById<Button>(R.id.buttonSave) }
    private val textViewTo by lazy { findViewById<TextView>(R.id.textViewTo) }
    private val textViewToLocation by lazy { findViewById<TextView>(R.id.textViewToLocation) }
    private val textViewItineraryResults by lazy { findViewById<TextView>(R.id.textViewItineraryResults) }
    private val textViewFrom by lazy { findViewById<TextView>(R.id.textViewFrom) }
    private val textViewFromLocation by lazy { findViewById<TextView>(R.id.textViewFromLocation) }
    private val textViewResult by lazy { findViewById<TextView>(R.id.textViewResult) }

    private val journeyPlannerItineraryResultDomainModel by lazy {
        intent.getSerializableExtra(
            EXTRA_JOURNEY_PLANNER_RESULT
        ) as JourneyPlannerResultDomainModel.Itinerary
    }

    private val recyclerViewItineraryResults by lazy {
        findViewById<RecyclerView>(R.id.recyclerViewItineraryResults).apply {
            layoutManager = viewManager
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }
    }
    private val viewManager by lazy { LinearLayoutManager(this) }
    private val viewAdapter by lazy {
        ItineraryResultsListAdapter { legs: List<Journey.Leg> ->
            itineraryResultsPresenter.onJourneyClicked(legs)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerary_results)
        textViewResult.movementMethod = ScrollingMovementMethod()
        recyclerViewItineraryResults.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
//        TODO("Sticky headers decoration. Let me know if needed")
        updateJourneys(journeyPlannerItineraryResultDomainModel.journeys)
    }

    override fun updateJourneys(journeys: List<Journey>) {
        viewAdapter.submitList(journeys)
    }

    override fun showDetailedActivity(detailedItineraryResults: List<Any>) {

    }

    override fun showMapActivity(mapItineraryResults: List<Any>) {

    }

    override fun showSaveJourneyActivity(journeyDetails: List<Any>) {

    }

    //    TODO("Remove later")
    override fun showResults() {
        textViewResult.text = journeyPlannerItineraryResultDomainModel.toString()
    }

    //    TODO("Do I need to make the list parcelable so it can be cast for getExtra()?")
    override fun showItineraryResultsLegsActivity(legs: List<Journey.Leg>) {
        startActivity(
            Intent(this, ItineraryResultsLegsActivity::class.java).putExtra(
                EXTRA_ITINERARY_RESULTS_LEGS,
                legs as Serializable
            )
        )
    }
}
