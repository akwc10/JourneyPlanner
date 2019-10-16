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
        val textViewLeg = view.findViewById<TextView>(R.id.textViewLeg)
        val textViewDeparturePoint = view.findViewById<TextView>(R.id.textViewDeparturePoint)
        val textViewArrivalPoint = view.findViewById<TextView>(R.id.textViewArrivalPoint)
        val textViewDuration = view.findViewById<TextView>(R.id.textViewDuration)
        textViewLeg.text = context.getString(R.string.textView_leg, (position + 1).toString())
        textViewDeparturePoint.text =
            context.getString(R.string.textView_departure_point, leg.departurePoint)
        textViewArrivalPoint.text =
            context.getString(R.string.textView_arrival_point, leg.arrivalPoint)
        textViewDuration.text = context.getString(
            R.string.textView_duration,
            Duration.between(leg.departureTime, leg.arrivalTime).toMinutes().toString()
        )
    }

    class LegDiffCallback : DiffUtil.ItemCallback<Leg>() {
        override fun areItemsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Leg, newItem: Leg) = oldItem == newItem
    }
}