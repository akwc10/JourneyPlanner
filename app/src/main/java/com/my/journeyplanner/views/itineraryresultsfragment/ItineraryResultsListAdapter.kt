package com.my.journeyplanner.views.itineraryresultsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R

class ItineraryResultsListAdapter(private val itemClickListener: (List<Journey.Leg>) -> Unit) :
    ListAdapter<Journey, ItineraryResultsListAdapter.MyViewHolder>(
        JourneyDiffCallback()
    ) {

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_text_views_itinerary_results, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.view
        val context = view.context
        val journey = getItem(position)

        view.findViewById<TextView>(R.id.textViewStartTime).text = context.getString(
            R.string.textView_start_time,
            journey.startDateTime.toLocalTime().toString()
        )
        view.findViewById<TextView>(R.id.textViewArrivalTime).text = context.getString(
            R.string.textView_arrival_time,
            journey.arrivalDateTime.toLocalTime().toString()
        )
        view.findViewById<TextView>(R.id.textViewLegs).text =
            context.getString(R.string.textView_legs, journey.legs.size.toString())
        view.findViewById<TextView>(R.id.textViewDuration).text =
            context.getString(R.string.textView_duration, journey.duration.toString())
        view.setOnClickListener { itemClickListener(journey.legs) }
    }

    class JourneyDiffCallback : DiffUtil.ItemCallback<Journey>() {
        override fun areItemsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Journey, newItem: Journey) = oldItem == newItem
    }
}