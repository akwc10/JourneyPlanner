package com.my.journeyplanner.views.detailedjourney

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.journeyplanner.helpers.addTwoColumnRow
import com.my.journeyplanner.views.results.EXTRA_DETAILED_JOURNEY
import com.my.presenter.detailedjourney.DetailedJourneyContract
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

private const val TUBE = "tube"

class DetailedJourneyActivity : AppCompatActivity(), DetailedJourneyContract.View {
    private val detailedJourney by lazy { intent.getSerializableExtra(EXTRA_DETAILED_JOURNEY) as Journey }
    private val cardViewDetailedJourney by lazy { findViewById<CustomCardView>(R.id.cardViewDetailedJourney) }
    private val linearLayoutLegs by lazy { cardViewDetailedJourney.findViewById<LinearLayout>(R.id.linearLayoutLegs) }
    private val timeFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_journey)
        updateDetailedJourney(detailedJourney)
    }

    override fun updateDetailedJourney(journey: Journey) {
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
            columnZeroText = "",
            columnOneText = leg.instructionSummary,
            columnOneTextStyle = Typeface.DEFAULT
        )
        return rowIndex1
    }

    private fun addDurationAndOptionalStop(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        val legDuration =
            Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
        linearLayoutLegs.addTwoColumnRow(
            rowIndex = rowIndex1++,
            columnZeroText = "",
            columnOneText =
            if (leg.mode == TUBE) {
                getString(
                    R.string.textView_duration_and_stops,
                    legDuration,
                    getString(R.string.mins),
                    getString(R.string.stops)
                )
            } else {
                getString(R.string.textView_duration, legDuration, getString(R.string.mins))
            }
        )
        return rowIndex1
    }

    private fun addStopPoints(rowIndex: Int, leg: Journey.Leg): Int {
        var rowIndex1 = rowIndex
        leg.path.stopPoints.dropLast(1).forEach { stopPoint ->
            linearLayoutLegs.addTwoColumnRow(
                rowIndex = rowIndex1++,
                columnZeroText = "-",
                columnOneText = stopPoint,
                columnZeroTextStyle = Typeface.DEFAULT,
                columnOneTextStyle = Typeface.DEFAULT
            )
        }
        return rowIndex1
    }

    private fun addModeAndArrivalPoint(rowIndex: Int, journey: Journey) {
        linearLayoutLegs.addTwoColumnRow(
            rowIndex = rowIndex,
            columnZeroText = journey.legs.last().mode,
            columnOneText = getString(
                R.string.textView_arrival_point_and_time,
                journey.legs.last().arrivalPoint,
                journey.arrivalDateTime.format(timeFormatter)
            )
        )
    }
}
