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
import org.threeten.bp.Duration

class ItineraryResultsLegsListAdapter :
    ListAdapter<Leg, ItineraryResultsLegsListAdapter.MyViewHolder>(LegDiffCallback()) {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewLeg: TextView = view.findViewById(R.id.textViewLeg)
        val textViewDeparturePoint: TextView = view.findViewById(R.id.textViewDeparturePoint)
        val textViewArrivalPoint: TextView = view.findViewById(R.id.textViewArrivalPoint)
        val textViewDuration: TextView = view.findViewById(R.id.textViewDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_text_views_itinerary_results_legs, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.view.context
        val leg = getItem(position)
        holder.textViewLeg.text =
            context.getString(R.string.textView_leg, (position + 1).toString())
        holder.textViewDeparturePoint.text =
            context.getString(R.string.textView_departure_point, leg.departurePoint)
        holder.textViewArrivalPoint.text =
            context.getString(R.string.textView_arrival_point, leg.arrivalPoint)
        holder.textViewDuration.text = context.getString(
            R.string.textView_duration,
            Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
        )
    }

    class LegDiffCallback : DiffUtil.ItemCallback<Leg>() {
        override fun areItemsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem
    }
}