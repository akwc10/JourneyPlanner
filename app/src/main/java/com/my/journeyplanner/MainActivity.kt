package com.my.journeyplanner

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.my.journeyplanner.views.results.ResultsActivity
import com.my.presenter.main.MainContract
import com.my.presenter.main.MainPresenter

const val EXTRA_FROM_LOCATION = "com.my.journeyplanner.FROM_LOCATION"
const val EXTRA_TO_LOCATION = "com.my.journeyplanner.TO_LOCATION"

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter by lazy { MainPresenter(this) }

    private val editTextFromLocation by lazy { findViewById<EditText>(R.id.editTextFromLocation) }
    private val editTextToLocation by lazy { findViewById<EditText>(R.id.editTextToLocation) }
    private val buttonChangeTime by lazy { findViewById<Button>(R.id.buttonChangeTime) }
    private val buttonEditPreferences by lazy { findViewById<Button>(R.id.buttonEditPreferences) }
    private val buttonPlanMyJourney by lazy { findViewById<Button>(R.id.buttonPlanMyJourney) }
    private val buttonMyJourneys by lazy { findViewById<Button>(R.id.buttonMyJourneys) }
    private val textViewResult by lazy { findViewById<TextView>(R.id.textViewResult) }

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
        textViewResult.movementMethod = ScrollingMovementMethod()
    }

    override fun getFromLocation() = editTextFromLocation.text.toString()

    override fun getToLocation() = editTextToLocation.text.toString()

    override fun showResultActivity() {
        startActivity(Intent(this, ResultsActivity::class.java).apply {
            putExtra(EXTRA_FROM_LOCATION, getFromLocation())
            putExtra(EXTRA_TO_LOCATION, getToLocation())
        })
    }

    override fun showResult(result: String) {
        textViewResult.text = result
    }

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
}