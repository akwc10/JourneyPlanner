package com.my.journeyplanner.views.detailedjourney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R

class LegsListAdapter : ListAdapter<Leg, LegsListAdapter.MyViewHolder>(
    LegDiffCallback()
) {
    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val linearLayoutLegs = view.findViewById<LinearLayout>(R.id.linearLayoutLegs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_journey, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.view.context
        val leg = getItem(position)
//        TODO("see DetailedJourneyActivity")
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