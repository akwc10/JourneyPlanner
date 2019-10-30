package com.my.journeyplanner.views.detailedjourney

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.journeyplanner.views.results.EXTRA_DETAILED_JOURNEY
import com.my.presenter.detailedjourney.DetailedJourneyContract
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

class DetailedJourneyActivity : AppCompatActivity(), DetailedJourneyContract.View {
    private val detailedJourney by lazy { intent.getSerializableExtra(EXTRA_DETAILED_JOURNEY) as Journey }
    private val cardViewDetailedJourney by lazy { findViewById<CardView>(R.id.cardViewDetailedJourney) }
    private val textViewStartAndArrivalTime by lazy {
        cardViewDetailedJourney.findViewById<TextView>(
            R.id.textViewStartAndArrivalTime
        )
    }
    private val textViewDuration by lazy { cardViewDetailedJourney.findViewById<TextView>(R.id.textViewDuration) }
    private val textViewMode by lazy { cardViewDetailedJourney.findViewById<TextView>(R.id.textViewMode) }
    private val textViewArrivalPoint by lazy { cardViewDetailedJourney.findViewById<TextView>(R.id.textViewArrivalPoint) }
    private val timeFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm") }
    private val recyclerViewLegs by lazy {
        findViewById<RecyclerView>(R.id.recyclerViewLegs).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }
    }
    private val viewAdapter by lazy { LegsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_journey)
        updateDetailedJourney(detailedJourney)
    }

    override fun updateDetailedJourney(journey: Journey) {
        addStartAndArrivalTimeAndDuration(journey)
        viewAdapter.submitList(journey.legs)
        addModeAndArrivalPoint(journey)
    }

    private fun addStartAndArrivalTimeAndDuration(journey: Journey) {
        textViewStartAndArrivalTime.text = getString(
            R.string.textView_start_and_arrival_time,
            journey.startDateTime.format(timeFormatter),
            journey.arrivalDateTime.format(timeFormatter)
        )
        textViewDuration.text = getString(
            R.string.textView_duration,
            Duration.between(journey.startDateTime, journey.arrivalDateTime)
                .toMinutes().toString(),
            getString(R.string.mins)
        )
    }

    private fun addModeAndArrivalPoint(journey: Journey) {
        textViewMode.text = journey.legs.last().mode
        textViewArrivalPoint.text = getString(
            R.string.textView_arrival_point_and_time,
            journey.legs.last().arrivalPoint,
            journey.arrivalDateTime.format(timeFormatter)
        )
    }
}
