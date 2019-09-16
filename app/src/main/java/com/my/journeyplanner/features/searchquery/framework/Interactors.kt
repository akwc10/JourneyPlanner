package com.my.journeyplanner.features.searchquery.framework

import com.my.journeyplanner.features.searchquery.interactors.CancelAsyncCall
import com.my.journeyplanner.features.searchquery.interactors.Enqueue
import com.my.journeyplanner.features.searchquery.interactors.GetJourneyResults

data class Interactors(
    val getJourneyResults: GetJourneyResults,
    val enqueue: Enqueue,
    val cancelAsyncCall: CancelAsyncCall
)