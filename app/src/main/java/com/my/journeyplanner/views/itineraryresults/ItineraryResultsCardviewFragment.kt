package com.my.journeyplanner.views.itineraryresults

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.journeyplanner.helpers.MarginItemDecoration
import com.my.presenter.itineraryresults.ItineraryResultsCardviewFragmentContract
import mu.KotlinLogging
import java.io.Serializable

const val DIVIDER_SPACE_HEIGHT = 32
private val logger = KotlinLogging.logger {}

class ItineraryResultsCardviewFragment : Fragment(), ItineraryResultsCardviewFragmentContract.View {
    private var listener: OnItineraryResultsCardviewFragmentInteractionListener? = null
    private val viewAdapter by lazy {
        ItineraryResultsCardviewListAdapter { journey: Journey -> onJourneyPressed(journey) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cardview, container, false)
        root.findViewById<RecyclerView>(R.id.recyclerViewItineraryResults).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }.addItemDecoration(MarginItemDecoration(DIVIDER_SPACE_HEIGHT))
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

    private fun onJourneyPressed(journey: Journey) {
        listener?.onItineraryResultsCardviewFragmentInteraction(journey)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItineraryResultsCardviewFragmentInteractionListener) {
            listener = context
        } else {
            throw IllegalStateException("$context must implement OnItineraryResultsCardviewFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface OnItineraryResultsCardviewFragmentInteractionListener {
        fun onItineraryResultsCardviewFragmentInteraction(journey: Journey)
    }

    companion object {
        @JvmStatic
        fun newInstance(journeys: List<Journey>) =
            ItineraryResultsCardviewFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_JOURNEYS_DOMAIN_MODEL, journeys as Serializable)
                }
            }
    }
}
