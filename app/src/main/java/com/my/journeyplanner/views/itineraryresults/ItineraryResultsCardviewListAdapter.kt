package com.my.journeyplanner.views.itineraryresults

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import mu.KotlinLogging
import org.threeten.bp.Duration
import org.threeten.bp.format.DateTimeFormatter

private val logger = KotlinLogging.logger {}

class ItineraryResultsCardviewListAdapter(private val itemClickListener: (Journey) -> Unit) :
    ListAdapter<Journey, ItineraryResultsCardviewListAdapter.MyViewHolder>(JourneyDiffCallback()) {

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val textViewJourney: TextView = view.findViewById(R.id.textViewJourney)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_card_view_journey, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.view.context
        val journey = getItem(position)
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        var formattedJourney =
            """${journey.startDateTime.format(timeFormatter)} - ${journey.arrivalDateTime.format(
                timeFormatter
            )}${Duration.between(journey.startDateTime, journey.arrivalDateTime)
                .toMinutes().toString().padStart(71, ' ')}${R.string.mins}

    """.trimMargin()
        journey.legs.forEach { leg ->
            formattedJourney += """

${leg.mode.padEnd(14 - leg.mode.length, ' ')}   ${leg.instructionSummary}
||${Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
                .padStart(18, ' ')}${R.string.mins}
""".trimMargin()
        }
        holder.textViewJourney.text = formattedJourney
        holder.view.setOnClickListener { itemClickListener(journey) }
    }
}

class JourneyDiffCallback : DiffUtil.ItemCallback<Journey>() {
    override fun areItemsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem
}