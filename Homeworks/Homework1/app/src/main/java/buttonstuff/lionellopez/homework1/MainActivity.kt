package buttonstuff.lionellopez.homework1

import android.app.Activity // used for hiding keyboard
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable // used for checking if EditText values change
import android.text.TextWatcher // used for checking if EditText Values change
import android.view.inputmethod.EditorInfo // used for checking if a button was pressed on digital keyboard
import kotlinx.android.synthetic.main.activity_main.* //used for direct id references
import android.view.View // used for hiding keyboard
import android.view.inputmethod.InputMethodManager // used for hiding keyboard
import android.widget.SeekBar
import kotlin.math.*


class MainActivity : AppCompatActivity() {

    private var initialBill = 0f
    private var p = 0f
    private var tip = 0f
    private var totalBill = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addListeners()
    }

    // closes digital keyboard
    private fun hideKeyboard() {
        val view: View = if (currentFocus == null) View(this) else currentFocus
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // approximates tip and total bill amount
    private fun calculateTip() {
        tip = (initialBill*p)/100
        tipAmount.text = "Tip: $$tip"
        totalBill = tip+initialBill
        totalCost.text = "Total Cost: $$totalBill"
    }

    // keeps the dollar sign while user is inputting numbers
    private fun keepPrefix(s:CharSequence) {
        val prefix = "$" // desired permanent character
        val cleanString: String
        val deletedPrefix: String
        if (!s.toString().startsWith(prefix)) {
            deletedPrefix = prefix.substring(0, prefix.length - 1)
            if (s.toString().startsWith(deletedPrefix)) {
                cleanString = s.toString().replace(deletedPrefix, "")
            } else {
                cleanString = s.toString().replace(prefix, "")
            }
            billAmount.setText(prefix + cleanString)
            billAmount.setSelection(prefix.length+1)
        }
    }

    // keeps the percentage sign while user is inputting numbers
    private fun keepSuffix(s:CharSequence) {
        val suffix = "%"
        val cleanString: String
        val deletedSuffix: String
        if (!s.toString().endsWith(suffix)) {
            deletedSuffix = suffix.substring(0, suffix.length - 1)
            if (s.toString().endsWith(deletedSuffix)) {
                cleanString = s.toString().replace(deletedSuffix, "")
            } else {
                cleanString = s.toString().replace(suffix, "")
            }
            percent.setText(cleanString+suffix)
            percent.setSelection(suffix.length)
        }
    }

    // displays rounded values for tip and totalBill
    private fun roundTip() {
        tip = totalBill - initialBill
        p = round((tip/initialBill)*100)
        percent.setText("$p%")
        tipAmount.text = "Tip: $$tip"
        totalCost.text = "Total Cost: $$totalBill"
    }

    // adds listeners to various widgets in activity_main
    private fun addListeners() {

        // Listens for user clicking Done button on digital keyboard
        billAmount.setOnEditorActionListener { v, actionId, event ->
            when(actionId) { // case switch for Done key
                EditorInfo.IME_ACTION_DONE -> {
                    hideKeyboard()
                    calculateTip()
                    true
                }
                else -> false
            }
        }

        percent.setOnEditorActionListener { v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    hideKeyboard()
                    calculateTip()
                    true
                }
                else -> false
            }
        }

        //checks for button clicks
        roundUp.setOnClickListener {
            totalBill = ceil(totalBill)
            roundTip()
        }

        roundDown.setOnClickListener {
            totalBill = floor(totalBill)
            roundTip()
        }

        // checks for changes to seek bar
        tipBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                calculateTip()
                percent.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })

        // Listens for change in Text
        billAmount.addTextChangedListener(object:TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            // updates text after when a change is made
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                initialBill = billAmount.text.removePrefix("$").toString().toFloat()
                keepPrefix(s)
            }
        })

        percent.addTextChangedListener(object:TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                p = percent.text.removeSuffix("%").toString().toFloat()
                keepSuffix(s)
            }
        })
    }
}
