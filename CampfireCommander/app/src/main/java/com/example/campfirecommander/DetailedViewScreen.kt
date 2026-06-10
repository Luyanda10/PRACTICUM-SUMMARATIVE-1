package com.example.campfirecommander

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//Luyanda Owethu Mnisi ST10518910
class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.detailedviewscreen)

        // Finding the container where we will put our list
        val container = findViewById<LinearLayout>(R.id.container)
        val btnBack = findViewById<Button>(R.id.Back)

        // Getting the lists from the first screen
        // These are parallel arrays
        val names = intent.getStringArrayListExtra("NAMES")
        val categories = intent.getStringArrayListExtra("CATEGORIES")
        val quantities = intent.getStringArrayListExtra("QUANTITIES")
        val comments = intent.getStringArrayListExtra("COMMENTS")

        // Checking if we actually got the data
        if (names != null && categories != null && quantities != null && comments != null) {
            
            // Loop through the arrays to show everything
            // Since they are parallel, we use the same index 'i'
            for (i in 0 until names.size) {
                val row = LinearLayout(this)
                row.orientation = LinearLayout.HORIZONTAL
                row.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                row.setPadding(0, 16, 0, 16)

                // Create text views for each part of the data
                row.addView(createTextView(names[i], 1f))
                row.addView(createTextView(categories[i], 1f))
                row.addView(createTextView(quantities[i], 0.6f))
                row.addView(createTextView(comments[i], 1f))

                // Adding the row to the screen
                container.addView(row)
            }
        }

        // Back button to go to the first screen
        btnBack.setOnClickListener {
            finish()
        }
    }

    // Helper function to make text views easily
    private fun createTextView(myText: String, myWeight: Float): TextView {
        val tv = TextView(this)
        tv.text = myText
        tv.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, myWeight)
        tv.textSize = 14f
        return tv
    }
}
