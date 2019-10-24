package com.my.journeyplanner.views.itineraryresults

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.journeyplanner.helpers.addLegRow
import mu.KotlinLogging
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

private val logger = KotlinLogging.logger {}

class ItineraryResultsListAdapter(private val itemClickListener: (Journey) -> Unit) :
    ListAdapter<Journey, ItineraryResultsListAdapter.MyViewHolder>(JourneyDiffCallback()) {

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val textViewStartAndArrivalTime: TextView =
            view.findViewById(R.id.textViewStartAndArrivalTime)
        val textViewDuration: TextView = view.findViewById(R.id.textViewDuration)
        val linearLayoutLegs: LinearLayout = view.findViewById(R.id.linearLayoutLegs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_journey, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.view.context
        val journey = getItem(position)
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        holder.textViewStartAndArrivalTime.text = context.getString(
            R.string.textView_start_and_arrival_time,
            journey.startDateTime.format(timeFormatter),
            journey.arrivalDateTime.format(timeFormatter)
        )
        holder.textViewDuration.text = context.getString(
            R.string.textView_duration,
            Duration.between(journey.startDateTime, journey.arrivalDateTime)
                .toMinutes().toString(),
            context.getString(R.string.mins)
        )
        journey.legs.forEachIndexed { index, leg ->
            holder.linearLayoutLegs.addLegRow(index * 2, leg.mode, leg.instructionSummary)
            holder.linearLayoutLegs.addLegRow(
                index * 2 + 1, "|", context.getString(
                    R.string.textView_duration,
                    Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString(),
                    context.getString(R.string.mins)
                )
            )
        }
        holder.view.setOnClickListener { itemClickListener(journey) }
    }
}

class JourneyDiffCallback : DiffUtil.ItemCallback<Journey>() {
    override fun areItemsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem
}