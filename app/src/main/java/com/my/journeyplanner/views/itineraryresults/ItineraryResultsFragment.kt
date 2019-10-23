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
import com.my.presenter.itineraryresults.ItineraryResultsFragmentContract
import mu.KotlinLogging
import java.io.Serializable

private const val EXTRA_JOURNEYS_DOMAIN_MODEL = "com.my.journeyplanner.JOURNEYS_DOMAIN_MODEL"
private const val DIVIDER_SPACE_HEIGHT = 32
private val logger = KotlinLogging.logger {}

class ItineraryResultsFragment : Fragment(), ItineraryResultsFragmentContract.View {
    private var listener: Listener? = null
    private val viewAdapter by lazy {
        ItineraryResultsListAdapter { journey: Journey -> onJourneyPressed(journey) }
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
        listener?.onItineraryResultsFragmentInteraction(journey)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw IllegalStateException("$context must implement Listener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface Listener {
        fun onItineraryResultsFragmentInteraction(journey: Journey)
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
