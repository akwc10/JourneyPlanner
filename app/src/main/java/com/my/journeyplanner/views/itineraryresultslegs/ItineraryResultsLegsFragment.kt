package com.my.journeyplanner.views.itineraryresultslegs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R
import com.my.presenter.itineraryresultslegs.ItineraryResultsLegsContract
import java.io.Serializable

private const val EXTRA_LEGS_DOMAIN_MODEL = "com.my.journeyplanner.LEGS_DOMAIN_MODEL"

class ItineraryResultsLegsFragment : Fragment(), ItineraryResultsLegsContract.View {
    private val viewAdapter by lazy { ItineraryResultsLegsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("UNCHECKED_CAST")
            updateLegs(it.getSerializable(EXTRA_LEGS_DOMAIN_MODEL) as List<Leg>)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_itinerary_results_legs, container, false)
        root.findViewById<RecyclerView>(R.id.recyclerViewItineraryResultsLegs).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            this.setHasFixedSize(true)
        }.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return root
    }

    override fun updateLegs(legs: List<Leg>) {
        viewAdapter.submitList(legs)
    }

    companion object {
        @JvmStatic
        fun newInstance(legs: List<Leg>) =
            ItineraryResultsLegsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_LEGS_DOMAIN_MODEL, legs as Serializable)
                }
            }
    }
}
