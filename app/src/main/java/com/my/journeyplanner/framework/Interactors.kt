package com.my.journeyplanner.framework

import com.my.core.interactors.BuildMockRetrofit
import com.my.core.interactors.CancelAsyncCall
import com.my.core.interactors.Enqueue
import com.my.core.interactors.GetJourneyResults

data class Interactors(
    val getJourneyResults: GetJourneyResults,
    val enqueue: Enqueue,
    val cancelAsyncCall: CancelAsyncCall,
    val buildMockRetrofit: BuildMockRetrofit
)