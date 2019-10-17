package com.my.journeyplanner.views.itineraryresults

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R
import com.my.presenter.itineraryresultsfragment.ItineraryResultsFragmentContract
import java.io.Serializable

private const val EXTRA_JOURNEYS_DOMAIN_MODEL = "com.my.journeyplanner.JOURNEYS_DOMAIN_MODEL"

class ItineraryResultsFragment : Fragment(), ItineraryResultsFragmentContract.View {
    private var listener: OnItineraryResultsFragmentInteractionListener? = null
    private val viewAdapter by lazy {
        ItineraryResultsListAdapter { legs: List<Leg> -> onJourneyPressed(legs) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_itinerary_results, container, false)
        root.findViewById<RecyclerView>(R.id.recyclerViewItineraryResults).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            @Suppress("UNCHECKED_CAST")
            updateJourneys(it.getSerializable(EXTRA_JOURNEYS_DOMAIN_MODEL) as List<Journey>)
        }
    }

    override fun updateJourneys(journeys: List<Journey>) {
        viewAdapter.submitList(journeys)
    }

    private fun onJourneyPressed(legs: List<Leg>) {
        listener?.onItineraryResultsFragmentInteraction(legs)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItineraryResultsFragmentInteractionListener) {
            listener = context
        } else {
            throw IllegalStateException("$context must implement OnItineraryResultsFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface OnItineraryResultsFragmentInteractionListener {
        fun onItineraryResultsFragmentInteraction(legs: List<Leg>)
    }

    companion object {
        @JvmStatic
        fun newInstance(journeys: List<Journey>) =
            ItineraryResultsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_JOURNEYS_DOMAIN_MODEL, journeys as Serializable)
                }
            }
    }
}
