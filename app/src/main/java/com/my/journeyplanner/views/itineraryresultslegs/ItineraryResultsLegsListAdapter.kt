package com.my.journeyplanner.views.itineraryresultslegs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R

class ItineraryResultsLegsListAdapter :
    ListAdapter<Leg, ItineraryResultsLegsListAdapter.MyViewHolder>(LegDiffCallback()) {
    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_text_views_itinerary_results_legs, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.view
        val context = view.context
        val leg = getItem(position)
        view.findViewById<TextView>(R.id.textViewLeg).text =
            context.getString(R.string.textView_leg, (position + 1).toString())
        view.findViewById<TextView>(R.id.textViewDeparturePoint).text =
            context.getString(R.string.textView_departure_point, leg.departurePoint)
        view.findViewById<TextView>(R.id.textViewArrivalPoint).text =
            context.getString(R.string.textView_arrival_point, leg.arrivalPoint)
        view.findViewById<TextView>(R.id.textViewDuration).text =
            context.getString(R.string.textView_duration, leg.duration.toString())
    }

    class LegDiffCallback : DiffUtil.ItemCallback<Leg>() {
        override fun areItemsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem
    }
}