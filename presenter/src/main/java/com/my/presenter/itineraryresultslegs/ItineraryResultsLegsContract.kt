package com.my.presenter.itineraryresultslegs

import com.my.presenter.base.BaseContract

class ItineraryResultsLegsContract {
    interface View : BaseContract.View {
        fun showResults()
    }

    interface Presenter : BaseContract.Presenter<View>
}