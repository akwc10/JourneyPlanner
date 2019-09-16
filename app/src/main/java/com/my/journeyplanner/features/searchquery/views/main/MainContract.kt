package com.my.journeyplanner.features.searchquery.views.main

import android.widget.EditText
import com.my.journeyplanner.features.searchquery.views.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun getFromLocation(): EditText
        fun getToLocation(): EditText
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onChangeTimeClicked()
        fun onEditPreferencesClicked()
        fun onPlanMyJourneyClicked()
        fun onMyJourneysClicked()
        fun cancelAsyncCall()
    }
}