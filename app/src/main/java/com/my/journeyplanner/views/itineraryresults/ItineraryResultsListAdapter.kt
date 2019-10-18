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
import org.threeten.bp.Duration

class ItineraryResultsListAdapter(private val itemClickListener: (List<Journey.Leg>) -> Unit) :
    ListAdapter<Journey, ItineraryResultsListAdapter.MyViewHolder>(JourneyDiffCallback()) {
    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val textViewStartTime: TextView = view.findViewById(R.id.textViewStartTime)
        val textViewArrivalTime: TextView = view.findViewById(R.id.textViewArrivalTime)
        val textViewLegs: TextView = view.findViewById(R.id.textViewLegs)
        val textViewDuration: TextView = view.findViewById(R.id.textViewDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_text_views_itinerary_results, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.view.context
        val journey = getItem(position)
        holder.textViewStartTime.text = context.getString(
            R.string.textView_start_time,
            journey.startDateTime.toLocalTime().toString()
        )
        holder.textViewArrivalTime.text = context.getString(
            R.string.textView_arrival_time,
            journey.arrivalDateTime.toLocalTime().toString()
        )
        holder.textViewLegs.text =
            context.getString(R.string.textView_legs, journey.legs.size.toString())
        holder.textViewDuration.text = context.getString(
            R.string.textView_duration,
            Duration.between(journey.startDateTime, journey.arrivalDateTime).toMinutes().toString()
        )
        holder.view.setOnClickListener { itemClickListener(journey.legs) }
    }

    class JourneyDiffCallback : DiffUtil.ItemCallback<Journey>() {
        override fun areItemsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem
    }
}