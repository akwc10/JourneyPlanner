package com.my.journeyplanner.views.itineraryresultsfragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.core.domain.JourneyPlannerResultDomainModel
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey
import com.my.journeyplanner.R
import com.my.presenter.itineraryresultsfragment.ItineraryResultsFragmentContract
import mu.KotlinLogging
import org.threeten.bp.LocalDateTime
import java.io.Serializable

private val logger = KotlinLogging.logger {}

private const val EXTRA_JOURNEY_PLANNER_RESULT_DOMAIN_MODEL =
    "com.my.journeyplanner.RESULT_DOMAIN_MODEL"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ItineraryResultsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ItineraryResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItineraryResultsFragment : Fragment(), ItineraryResultsFragmentContract.View {

    private val viewManager by lazy { LinearLayoutManager(context) }
    private val viewAdapter by lazy {
        ItineraryResultsListAdapter { legs: List<Journey.Leg> ->
            //                itineraryResultsPresenter.onJourneyClicked(legs)
        }
    }

    private lateinit var journeyPlannerResultDomainModel: List<Journey>
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            journeyPlannerResultDomainModel =
                it.getSerializable(EXTRA_JOURNEY_PLANNER_RESULT_DOMAIN_MODEL) as List<Journey>
        }
        logger.info { "journeyPlannerResultDomainModel: $journeyPlannerResultDomainModel" }
        val testJourneys = listOf(
            Journey(
                arrivalDateTime = LocalDateTime.now(), duration = 9, legs = listOf(
                    Journey.Leg(arrivalPoint = "arr", departurePoint = "dep", duration = 99)
                )
                , startDateTime = LocalDateTime.now()
            )
        )
        updateJourneys(testJourneys)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_itinerary_results, container, false)

        val recyclerViewItineraryResults =
            root.findViewById<RecyclerView>(R.id.recyclerViewItineraryResults).apply {
                layoutManager = viewManager
                adapter = viewAdapter
                this.setHasFixedSize(true)
            }
        recyclerViewItineraryResults.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return root
    }

    override fun updateJourneys(journeys: List<Journey>) {
        viewAdapter.submitList(journeys)
    }

    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItineraryResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(result: List<JourneyPlannerResultDomainModel.Itinerary.Journey>) =
            ItineraryResultsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        EXTRA_JOURNEY_PLANNER_RESULT_DOMAIN_MODEL,
                        result as Serializable
                    )
                }
            }
    }
}
