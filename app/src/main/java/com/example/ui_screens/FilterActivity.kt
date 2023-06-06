package com.example.ui_screens

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import com.nex3z.flowlayout.FlowLayout

class FilterActivity : AppCompatActivity() {

    private lateinit var editTextProfession: EditText
    private lateinit var editTextUniversity: EditText
    private lateinit var editTextCompany: EditText
    private lateinit var edHomeTown: EditText
    private lateinit var edlivesIn: EditText
    private lateinit var edHobbies: EditText
    private lateinit var edMovies: EditText
    private lateinit var edFood: EditText
    private lateinit var edSports: EditText
    private lateinit var buttonSave: Button
    private lateinit var rangeSlider: RangeSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        // Initialize views
        editTextProfession = findViewById(R.id.editTextProfession)
        editTextUniversity = findViewById(R.id.editTextUniversity)
        editTextCompany = findViewById(R.id.editTextCompany)
        edHomeTown = findViewById(R.id.edHomeTown)
        edlivesIn = findViewById(R.id.edlivesIn)
        edHobbies = findViewById(R.id.edHobbies)
        edMovies = findViewById(R.id.edMovies)
        edFood = findViewById(R.id.edFood)
        edSports = findViewById(R.id.edSports)
        buttonSave = findViewById(R.id.buttonSave)

        // Set button click listener
        buttonSave.setOnClickListener {
            // Perform action when the button is clicked
        }

        // Add options to flow layout
        val flowLayoutOption = findViewById<FlowLayout>(R.id.flowLayoutOption)
                val options = listOf("Male", "Female", "Transgender")

        for (option in options) {
            val textView = createOptionTextView(option)
            flowLayoutOption.addView(textView)
        }

        val flowLayoutOption2 = findViewById<FlowLayout>(R.id.flowLayoutOption2)
        val options2 = listOf("Coffee", "Business", "Hobbies", "Friendship", "Movies", "Dinning", "Dating", "Matrimony")

        for (option in options2) {
            val textView = createOptionTextView2(option)
            flowLayoutOption2.addView(textView)
        }

        rangeSlider = findViewById(R.id.seekBar)

        // Set the range values and initial range
        rangeSlider.valueFrom = 0f
        rangeSlider.valueTo = 100f
        rangeSlider.setValues(0f, 100f)

        // Set a listener to get the selected range
        rangeSlider.addOnChangeListener { slider, _, _ ->
            val selectedMin = slider.values[0].toInt()
            val selectedMax = slider.values[1].toInt()
            // Do something with the selected range values
            // For example, update a TextView with the selected range
            updateSelectedRange(selectedMin, selectedMax)
        }

    }

    private fun updateSelectedRange(min: Int, max: Int) {
        // Update the UI with the selected range
        // For example, update a TextView
        val selectedRangeTextView: TextView = findViewById(R.id.selectedRangeTextView)
        selectedRangeTextView.text = "$min - $max"
    }

    private fun createOptionTextView(text: String): TextView {
        val textView = TextView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 8, 18, 48) // Set the margins between options
        textView.layoutParams = params
        textView.text = text
        textView.setBackgroundResource(R.drawable.card_background) // Set the background resource for selectable text
        textView.setOnClickListener {
            if (!textView.isSelected || countSelectedTextViews() > 1) {
                textView.isSelected = !textView.isSelected
                updateTextBackground(textView)
            }
        }
        updateTextBackground(textView) // Update the background initially
        return textView
    }

    private fun countSelectedTextViews(): Int {
        var count = 0
        val flowLayoutOptions = findViewById<FlowLayout>(R.id.flowLayoutOption)
        for (i in 0 until flowLayoutOptions.childCount) {
            val child = flowLayoutOptions.getChildAt(i)
            if (child is TextView && child.isSelected) {
                count++
            }
        }
        return count
    }

    private fun updateTextBackground(textView: TextView) {
        val selectedColor = ContextCompat.getColor(this, R.color.highlightColor)
        val deselectedColor = ContextCompat.getColor(this, android.R.color.transparent)
        val selectedTextColor = Color.WHITE
        val deselectedTextColor = Color.BLACK
        val backgroundDrawable = textView.background as GradientDrawable

        // Set the background color based on the selected state
        if (textView.isSelected) {
            backgroundDrawable.setColor(selectedColor)
            textView.setTextColor(selectedTextColor)
            textView.setPadding(20, 12, 20, 12) // Add padding for selected state
        } else {
            backgroundDrawable.setColor(deselectedColor)
            textView.setTextColor(deselectedTextColor)
            textView.setPadding(20,12, 20, 12) // Add padding for deselected state
        }
    }

    private fun createOptionTextView2(text: String): TextView {
        val textView = TextView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 8, 18, 48) // Set the margins between options
        textView.layoutParams = params
        textView.text = text
        textView.setBackgroundResource(R.drawable.card_background) // Set the background resource for selectable text
        textView.setOnClickListener {
            if (!textView.isSelected || countSelectedTextViews2() > 1) {
                textView.isSelected = !textView.isSelected
                updateTextBackground2(textView)
            }
        }
        updateTextBackground(textView) // Update the background initially
        return textView
    }

    private fun countSelectedTextViews2(): Int {
        var count = 0
        val flowLayoutOptions = findViewById<FlowLayout>(R.id.flowLayoutOption2)
        for (i in 0 until flowLayoutOptions.childCount) {
            val child = flowLayoutOptions.getChildAt(i)
            if (child is TextView && child.isSelected) {
                count++
            }
        }
        return count
    }

    private fun updateTextBackground2(textView: TextView) {
        val selectedColor = ContextCompat.getColor(this, R.color.highlightColor)
        val deselectedColor = ContextCompat.getColor(this, android.R.color.transparent)
        val selectedTextColor = Color.WHITE
        val deselectedTextColor = Color.BLACK
        val backgroundDrawable = textView.background as GradientDrawable

        // Set the background color based on the selected state
        if (textView.isSelected) {
            backgroundDrawable.setColor(selectedColor)
            textView.setTextColor(selectedTextColor)
            textView.setPadding(20, 12, 20, 12) // Add padding for selected state
        } else {
            backgroundDrawable.setColor(deselectedColor)
            textView.setTextColor(deselectedTextColor)
            textView.setPadding(20,12, 20, 12) // Add padding for deselected state
        }
    }

}
