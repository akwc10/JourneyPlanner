package com.my.journeyplanner.views.main

import android.widget.EditText
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import com.my.journeyplanner.views.base.BaseContract
import retrofit2.Call

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): EditText
        fun getToLocation(): EditText
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onChangTimeClicked()
        fun onEditPreferencesClicked()
        fun onPlanMyJourneyClicked(): Call<JourneyPlannerDisambiguationResult>
        fun onMyJourneysClicked()
    }
}