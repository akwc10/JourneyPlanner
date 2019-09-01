package com.my.journeyplanner

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.my.journeyplanner.presenters.MainPresenter
import com.my.journeyplanner.usecases.BuildMockRetrofit
import com.my.journeyplanner.usecases.GetJourneyResults
import com.my.journeyplanner.views.main.MainContract

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter by lazy {
        MainPresenter(this, GetJourneyResults(), BuildMockRetrofit())
    }

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
//        editTextFromLocation.setText("Marylebone Station")
//        editTextToLocation.setText("Liverpool Street")
        editTextFromLocation.setText("Leicester Square Underground Station")
        editTextToLocation.setText("Liverpool Street Underground Station")
//        editTextFromLocation.setText("bfdbfdbdfbfd")
//        editTextToLocation.setText("bfdbfdbdfbfbd")
    }

    override fun getFromLocation(): EditText = editTextFromLocation

    override fun getToLocation(): EditText = editTextToLocation

    private fun addEditTextToListener() {
        editTextToLocation.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                mainPresenter.onPlanMyJourneyClicked()
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

    override fun onStop() {
        super.onStop()
        mainPresenter.cancelAsyncCall()
    }
}
