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
import com.my.journeyplanner.R
import com.my.presenter.itineraryresultsfragment.ItineraryResultsFragmentContract
import mu.KotlinLogging
import java.io.Serializable

private const val EXTRA_JOURNEY_PLANNER_JOURNEYS_RESULT_DOMAIN_MODEL =
    "com.my.journeyplanner.JOURNEYS_RESULTS_DOMAIN_MODEL"

private val logger = KotlinLogging.logger {}

class ItineraryResultsFragment : Fragment(), ItineraryResultsFragmentContract.View {

    private val viewAdapter by lazy {
        ItineraryResultsListAdapter { legs: List<Journey.Leg> ->
            onJourneyPressed(legs)
        }
    }

    private var listener: OnItineraryResultsFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("UNCHECKED_CAST")
            updateJourneys(it.getSerializable(EXTRA_JOURNEY_PLANNER_JOURNEYS_RESULT_DOMAIN_MODEL) as List<Journey>)
        }
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

    override fun updateJourneys(journeys: List<Journey>) {
        viewAdapter.submitList(journeys)
    }

    private fun onJourneyPressed(legs: List<Journey.Leg>) {
        listener?.onFragmentInteraction(legs)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItineraryResultsFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItineraryResultsFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnItineraryResultsFragmentInteractionListener {
        fun onFragmentInteraction(legs: List<Journey.Leg>)
    }

    companion object {
        @JvmStatic
        fun newInstance(result: List<Journey>) =
            ItineraryResultsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        EXTRA_JOURNEY_PLANNER_JOURNEYS_RESULT_DOMAIN_MODEL,
                        result as Serializable
                    )
                }
            }
    }
}
