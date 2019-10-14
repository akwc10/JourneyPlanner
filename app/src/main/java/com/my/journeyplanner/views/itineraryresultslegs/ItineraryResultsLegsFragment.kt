package com.my.journeyplanner.views.itineraryresultslegs

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.my.core.domain.JourneyPlannerResultDomainModel.Itinerary.Journey.Leg
import com.my.journeyplanner.R
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val EXTRA_JOURNEY_PLANNER_LEGS_RESULT_DOMAIN_MODEL =
    "com.my.journeyplanner.LEGS_RESULTS_DOMAIN_MODEL"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ItineraryResultsLegsFragment.OnItineraryResultsLegsFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ItineraryResultsLegsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItineraryResultsLegsFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var listener: OnItineraryResultsFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("UNCHECKED_CAST")
            val legs =
                it.getSerializable(EXTRA_JOURNEY_PLANNER_LEGS_RESULT_DOMAIN_MODEL) as List<Leg>
            Toast.makeText(context, legs.toString(), Toast.LENGTH_SHORT).show()
//            TODO("listAdapter")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_itinerary_results_legs, container, false)
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnItineraryResultsFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnItineraryResultsFragmentInteractionListener")
//        }
//    }
//
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
    interface OnItineraryResultsLegsFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ItineraryResultsLegsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(legs: List<Leg>) =
            ItineraryResultsLegsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        EXTRA_JOURNEY_PLANNER_LEGS_RESULT_DOMAIN_MODEL,
                        legs as Serializable
                    )
                }
            }
    }
}
