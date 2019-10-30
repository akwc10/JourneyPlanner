package com.my.journeyplanner.views.detailedjourney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R
import com.my.journeyplanner.helpers.addTwoColumnRow
import mu.KotlinLogging
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

private const val TUBE = "tube"
private const val BUS = "bus"
private val logger = KotlinLogging.logger {}

class LegsListAdapter : ListAdapter<Leg, LegsListAdapter.MyViewHolder>(LegDiffCallback()) {
    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val recyclerViewLegs: RecyclerView = view.findViewById(R.id.recyclerViewLegs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_detailed_journey, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val leg = getItem(position)
        var rowIndex = 0
        holder.recyclerViewLegs.apply {
            rowIndex = this.addModeAndDeparturePoint(rowIndex = rowIndex, leg = leg)
            rowIndex = this.addInstructionSummary(rowIndex = rowIndex, leg = leg)
            rowIndex = this.addDurationAndOptionalStop(rowIndex = rowIndex, leg = leg)
            rowIndex = this.addStopPoints(rowIndex = rowIndex, leg = leg)
        }
    }

    private fun ViewGroup.addModeAndDeparturePoint(rowIndex: Int, leg: Leg): Int {
        var rowIndex1 = rowIndex
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        this.addTwoColumnRow(
            rowIndex = rowIndex1++,
            layoutResourceId = R.layout.two_columns_row_bold,
            columnZeroText = leg.mode,
            columnOneText = context.getString(
                R.string.textView_departure_point_and_time,
                leg.departurePoint,
                leg.departureTime.format(timeFormatter)
            )
        )
        return rowIndex1
    }

    private fun ViewGroup.addInstructionSummary(rowIndex: Int, leg: Leg): Int {
        var rowIndex1 = rowIndex
        this.addTwoColumnRow(
            rowIndex = rowIndex1++,
            layoutResourceId = R.layout.two_columns_row_bold_then_default,
            columnOneText = leg.instructionSummary
        )
        return rowIndex1
    }

    private fun ViewGroup.addDurationAndOptionalStop(rowIndex: Int, leg: Leg): Int {
        var rowIndex1 = rowIndex
        val legDuration =
            Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
        if ((leg.mode == TUBE || leg.mode == BUS) && leg.path.stopPoints.size > 1) {
            this.addTwoColumnRow(
                rowIndex = rowIndex1++,
                layoutResourceId = R.layout.two_columns_row_duration_and_stops,
                columnZeroText = context.getString(
                    R.string.textView_duration,
                    legDuration,
                    context.getString(R.string.mins)
                ),
                columnOneText = context.getString(R.string.textView_stops)
            )
        } else {
            this.addTwoColumnRow(
                rowIndex = rowIndex1++,
                layoutResourceId = R.layout.two_columns_row_default_then_bold,
                columnOneText = context.getString(
                    R.string.textView_duration,
                    legDuration,
                    context.getString(R.string.mins)
                )
            )
        }
        return rowIndex1
    }

    private fun ViewGroup.addStopPoints(rowIndex: Int, leg: Leg): Int {
        var rowIndex1 = rowIndex
        leg.path.stopPoints.dropLast(1).forEach { stopPoint ->
            this.addTwoColumnRow(
                rowIndex = rowIndex1++,
                columnZeroText = "-",
                columnOneText = stopPoint
            )
        }
        return rowIndex1
    }

    class LegDiffCallback : DiffUtil.ItemCallback<Leg>() {
        override fun areItemsTheSame(oldItem: Leg, newItem: Leg) = oldItem.id() == newItem.id()

        private fun Leg.id() = Pair(
            Pair(this.departurePoint, this.departureTime),
            Pair(this.arrivalPoint, this.arrivalTime)
        )

        override fun areContentsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem
    }
}
