package com.my.journeyplanner.views.results

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.EXTRA_FROM_LOCATION
import com.my.journeyplanner.EXTRA_TO_LOCATION
import com.my.journeyplanner.R
import com.my.journeyplanner.views.itineraryresults.ItineraryResultsFragment
import com.my.journeyplanner.views.itineraryresultslegs.ItineraryResultsLegsFragment
import com.my.presenter.results.ResultsContract
import com.my.presenter.results.ResultsPresenter
import com.my.repository.JourneyPlannerRepository

class ResultsActivity : AppCompatActivity(), ResultsContract.View,
    ItineraryResultsFragment.OnItineraryResultsFragmentInteractionListener {
    private val fromLocation by lazy { intent.getStringExtra(EXTRA_FROM_LOCATION) }
    private val toLocation by lazy { intent.getStringExtra(EXTRA_TO_LOCATION) }
    private val itineraryResultsPresenter by lazy {
        ResultsPresenter(this, JourneyPlannerRepository())
    }
    private val buttonShowMap by lazy { findViewById<Button>(R.id.buttonShowMap) }
    private val buttonSave by lazy { findViewById<Button>(R.id.buttonSave) }
    private val textViewTo by lazy { findViewById<TextView>(R.id.textViewTo) }
    private val textViewToLocation by lazy { findViewById<TextView>(R.id.textViewToLocation) }
    private val textViewItineraryResults by lazy { findViewById<TextView>(R.id.textViewItineraryResults) }
    private val textViewFrom by lazy { findViewById<TextView>(R.id.textViewFrom) }
    private val textViewFromLocation by lazy { findViewById<TextView>(R.id.textViewFromLocation) }
    private val textViewResult by lazy { findViewById<TextView>(R.id.textViewResult) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        textViewResult.movementMethod = ScrollingMovementMethod()
        itineraryResultsPresenter.getJourneyResults(fromLocation, toLocation)
    }

    override fun showMapActivity(mapItineraryResults: List<Any>) {

    }

    override fun showSaveJourneyActivity(journeyDetails: List<Any>) {

    }

    override fun showItineraryResultsFragment(journeys: List<Journey>) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentResults, ItineraryResultsFragment.newInstance(journeys)).commit()
    }

    override fun showItineraryResultsLegsFragment(legs: List<Journey.Leg>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentResults, ItineraryResultsLegsFragment.newInstance(legs))
            .addToBackStack(null).commit()
    }

    override fun showDisambiguationResultsFragment(disambiguationOptions: JourneyPlannerResultDomainModel.FromToDisambiguationOptions) {

    }

    override fun showNoResultsFragment() {

    }

    override fun showErrorFragment(error: String) {

    }

    override fun onItineraryResultsFragmentInteraction(legs: List<Journey.Leg>) {
        showItineraryResultsLegsFragment(legs)
    }
}
