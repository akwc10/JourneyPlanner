package com.my.journeyplanner.views.detailedjourney

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.journeyplanner.helpers.addTwoColumnRow
import com.my.journeyplanner.views.results.EXTRA_DETAILED_JOURNEY
import com.my.presenter.detailedjourney.DetailedJourneyContract
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

private const val TUBE = "tube"
private const val BUS = "bus"

class DetailedJourneyActivity : AppCompatActivity(), DetailedJourneyContract.View {
    private val detailedJourney by lazy { intent.getSerializableExtra(EXTRA_DETAILED_JOURNEY) as Journey }
    private val cardViewDetailedJourney by lazy { findViewById<CustomCardView>(R.id.cardViewDetailedJourney) }
    private val linearLayoutLegs by lazy { cardViewDetailedJourney.findViewById<LinearLayout>(R.id.linearLayoutLegs) }
    private val timeFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm") }
    private val viewAdapter by lazy { LegsListAdapter() }
    private val recyclerViewLegs by lazy {
        findViewById<RecyclerView>(R.id.recyclerViewLegs).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_journey)
        updateDetailedJourney(detailedJourney)
    }

    override fun updateDetailedJourney(journey: Journey) {
//        viewAdapter.submitList(journey.legs)

        addStartAndArrivalTimeAndDuration(journey)
        var rowIndex = 0
        journey.legs.forEach { leg ->
            rowIndex = addModeAndDeparturePoint(rowIndex, leg)
            rowIndex = addInstructionSummary(rowIndex, leg)
            rowIndex = addDurationAndOptionalStop(rowIndex, leg)
            rowIndex = addStopPoints(rowIndex, leg)
        }
        addModeAndArrivalPoint(rowIndex, journey)
    }

    private fun addStartAndArrivalTimeAndDuration(journey: Journey) {
        findViewById<TextView>(R.id.textViewStartAndArrivalTime).text = getString(
            R.string.textView_start_and_arrival_time,
            journey.startDateTime.format(timeFormatter),
            journey.arrivalDateTime.format(timeFormatter)
        )
        findViewById<TextView>(R.id.textViewDuration).text = getString(
            R.string.textView_duration,
            Duration.between(journey.startDateTime, journey.arrivalDateTime)
                .toMinutes().toString(),
            getString(R.string.mins)
        )
    }

    private fun addModeAndDeparturePoint(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        linearLayoutLegs.addTwoColumnRow(
            rowIndex = rowIndex1++,
            layoutResourceId = R.layout.two_columns_row_bold,
            columnZeroText = leg.mode,
            columnOneText = getString(
                R.string.textView_departure_point_and_time,
                leg.departurePoint,
                leg.departureTime.format(timeFormatter)
            )
        )
        return rowIndex1
    }

    private fun addInstructionSummary(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        linearLayoutLegs.addTwoColumnRow(
            rowIndex = rowIndex1++,
            layoutResourceId = R.layout.two_columns_row_bold_then_default,
            columnOneText = leg.instructionSummary
        )
        return rowIndex1
    }

    private fun addDurationAndOptionalStop(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        val legDuration =
            Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
        if ((leg.mode == TUBE || leg.mode == BUS) && leg.path.stopPoints.size > 1) {
            linearLayoutLegs.addTwoColumnRow(
                rowIndex = rowIndex1++,
                layoutResourceId = R.layout.two_columns_row_duration_and_stops,
                columnZeroText = getString(
                    R.string.textView_duration,
                    legDuration,
                    getString(R.string.mins)
                ),
                columnOneText = getString(R.string.textView_stops)
            )
        } else {
            linearLayoutLegs.addTwoColumnRow(
                rowIndex = rowIndex1++,
                layoutResourceId = R.layout.two_columns_row_default_then_bold,
                columnOneText = getString(
                    R.string.textView_duration,
                    legDuration,
                    getString(R.string.mins)
                )
            )
        }
        return rowIndex1
    }

    private fun addStopPoints(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        leg.path.stopPoints.dropLast(1).forEach { stopPoint ->
            linearLayoutLegs.addTwoColumnRow(
                rowIndex = rowIndex1++,
                columnZeroText = "-",
                columnOneText = stopPoint
            )
        }
        return rowIndex1
    }

    private fun addModeAndArrivalPoint(rowIndex: Int, journey: Journey) {
        linearLayoutLegs.addTwoColumnRow(
            rowIndex = rowIndex,
            layoutResourceId = R.layout.two_columns_row_bold,
            columnZeroText = journey.legs.last().mode,
            columnOneText = getString(
                R.string.textView_arrival_point_and_time,
                journey.legs.last().arrivalPoint,
                journey.arrivalDateTime.format(timeFormatter)
            )
        )
    }
}
