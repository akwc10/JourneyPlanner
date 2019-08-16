package com.my.journeyplanner

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.my.journeyplanner.helpers.JourneyPlannerApiService
import com.my.journeyplanner.models.JourneyPlannerDisambiguationResult
import com.my.journeyplanner.presenters.MainPresenter
import com.my.journeyplanner.views.main.MainContract
import retrofit2.Call

val apiService by lazy { JourneyPlannerApiService.createApiService }
var call: Call<JourneyPlannerDisambiguationResult>? = null

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter by lazy { MainPresenter(this) }

    private val editTextFromLocation by lazy { findViewById<EditText>(R.id.editTextFromLocation) }
    private val editTextToLocation by lazy { findViewById<EditText>(R.id.editTextToLocation) }
    private val buttonChangeTime by lazy { findViewById<Button>(R.id.buttonChangeTime) }
    private val buttonEditPreferences by lazy { findViewById<Button>(R.id.buttonEditPreferences) }
    private val buttonPlanMyJourney by lazy { findViewById<Button>(R.id.buttonPlanMyJourney) }
    private val buttonMyJourneys by lazy { findViewById<Button>(R.id.buttonMyJourneys) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addEditTextToListener()
        addPlanMyJourneyButtonListener()

//        TODO("REMOVE after test")
        editTextFromLocation.setText("Marylebone Station")
        editTextToLocation.setText("Liverpool Street")
    }

    override fun getFromLocation(): EditText = editTextFromLocation

    override fun getToLocation(): EditText = editTextToLocation

    private fun addEditTextToListener() {
        editTextToLocation.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                call = mainPresenter.onPlanMyJourneyClicked()
                true
            } else {
                false
            }
        }
    }

    private fun addPlanMyJourneyButtonListener() {
        buttonPlanMyJourney.setOnClickListener {
            mainPresenter.onPlanMyJourneyClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
    }
}
