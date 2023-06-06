package com.example.ui_screens

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.slider.RangeSlider
import com.nex3z.flowlayout.FlowLayout

class RefineActivity : AppCompatActivity() {

    private lateinit var spinnerAvailability: Spinner
    private lateinit var editStatus: EditText
  //  private lateinit var seekBarDistance: SeekBar
    private lateinit var rangeSlider: RangeSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refine)

        // Initialize views
        spinnerAvailability = findViewById(R.id.spinnerAvailability)
        editStatus = findViewById(R.id.editStatus)
      //  seekBarDistance = findViewById(R.id.seekBarDistance)

        val editStatus = findViewById<EditText>(R.id.editStatus)
        val textCharacterCount = findViewById<TextView>(R.id.textCharacterCount)

        editStatus.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the character count text
                val currentLength = s?.length ?: 0
                textCharacterCount.text = "$currentLength/250"
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used in this example
            }
        })


        rangeSlider = findViewById(R.id.seekBarDistance)

        rangeSlider.valueFrom = 0f
        rangeSlider.valueTo = 100f
        rangeSlider.setValues(0f)

        // Set a listener to get the selected range
        rangeSlider.addOnChangeListener { slider, _, _ ->
            val selected = slider.values[0].toInt()
        }


        // Set up the spinner for availability
        val availabilityOptions = resources.getStringArray(R.array.availability_options)
        val availabilityAdapter = ArrayAdapter(
            this,
            R.layout.spinner_dropdown_item, // Use custom layout for dropdown items
            availabilityOptions
        )
        spinnerAvailability.adapter = availabilityAdapter


        // Set up the seek bar for hyperlocal distance
       // seekBarDistance.max = 100

        // Set up click listeners for buttons

        val flowLayoutOptions = findViewById<FlowLayout>(R.id.flowLayoutOptions)

        val options = listOf("Coffee", "Business", "Hobbies", "Friendship", "Movies", "Dinning", "Dating", "Matrimony")

        for (option in options) {
            val textView = createOptionTextView(option)
            flowLayoutOptions.addView(textView)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_item1 -> {
                    // Handle navigation item 1
                    true
                }
                R.id.navigation_item2 -> {
                    // Handle navigation item 2
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_item3 -> {
                    // Handle navigation item 3
                    true
                }
                R.id.navigation_item4 -> {
                    // Handle navigation item 4
                    true
                }
                R.id.navigation_item5 -> {
                    // Handle navigation item 5
                    true
                }
                else -> false
            }
        }
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
        val flowLayoutOptions = findViewById<FlowLayout>(R.id.flowLayoutOptions)
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit Confirmation")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
                finishAffinity() // Finish all activities and exit the application
            }
            .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .show()
    }


}
