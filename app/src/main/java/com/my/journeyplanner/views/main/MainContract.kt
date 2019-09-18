package com.my.journeyplanner.views.main

import android.widget.EditText
import com.my.core.domain.JourneyPlannerResult
import com.my.journeyplanner.framework.JourneyPlannerResultType
import com.my.journeyplanner.views.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): EditText
        fun getToLocation(): EditText
        fun showItineraryResultActivity()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onChangeTimeClicked()
        fun onEditPreferencesClicked()
        fun onPlanMyJourneyClicked()
        fun onMyJourneysClicked()
        fun cancelAsyncCall()
        fun updateJourneyPlannerResult(journeyPlannerResult: JourneyPlannerResult?)
        fun updateJourneyPlannerResultType(journeyPlannerResultType: JourneyPlannerResultType?)
        fun getJourneyPlannerResult(): JourneyPlannerResult?
        fun getJourneyPlannerResultType(): JourneyPlannerResultType?
    }
}