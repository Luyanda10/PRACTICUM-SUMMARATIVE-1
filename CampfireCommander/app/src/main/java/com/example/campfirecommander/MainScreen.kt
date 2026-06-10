package com.example.campfirecommander

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
 //Luyanda Owethu Mnisi ST10518910
class MainActivity : AppCompatActivity() {

    // I am using 4 parallel arrays to store my data
    // The teacher said parallel arrays are good for storing different parts of the same item
    private val itemNames = ArrayList<String>()
    private val itemCategories = ArrayList<String>()
    private val itemQuantities = ArrayList<String>()
    private val itemComments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mainscreen)

        // Finding all my UI pieces by their ID
        val etItem = findViewById<EditText>(R.id.etItem)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val spinnerQuantity = findViewById<Spinner>(R.id.spinnerQuantity)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val tvTotalItems = findViewById<TextView>(R.id.tvTotalItems)
        val btnAddGear = findViewById<Button>(R.id.btnAddGear)
        val btnViewList = findViewById<Button>(R.id.btnViewList)

        // Putting numbers into my spinner for the user to pick
        val numberList = ArrayList<String>()
        for (i in 1..20) {
            numberList.add(i.toString())
        }
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numberList)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuantity.adapter = myAdapter

        // This is what happens when I click the "Add Gear" button
        btnAddGear.setOnClickListener {
            // Getting the text from the edit boxes
            val name = etItem.text.toString()
            val cat = etCategory.text.toString()
            val qty = spinnerQuantity.selectedItem.toString()
            val note = etNotes.text.toString()

            // ERROR HANDLING: making sure everything is typed in!
            if (name.equals("") || cat.equals("") || note.equals("")) {
                // If something is empty, show a warning toast
                Toast.makeText(this, "Please fill in EVERY box before adding!", Toast.LENGTH_SHORT).show()
            } else {
                // If it is okay, add to my parallel arrays
                itemNames.add(name)
                itemCategories.add(cat)
                itemQuantities.add(qty)
                itemComments.add(note)

                // CALCULATING TOTAL: using a loop to sum them up
                var currentTotal = 0
                for (index in 0 until itemNames.size) {
                    // I am adding the quantity from each spot in the array
                    // I have to change it to an Int first
                    val amount = itemQuantities[index].toInt()
                    currentTotal = currentTotal + amount
                }
                
                // Updating the label on the screen
                tvTotalItems.text = "Total items: " + currentTotal

                // Clearing all my inputs so the user can type again
                etItem.text.clear()
                etCategory.text.clear()
                etNotes.text.clear()
                spinnerQuantity.setSelection(0)

                // Showing success message
                Toast.makeText(this, "Your gear was added to the list!", Toast.LENGTH_SHORT).show()
            }
        }

        // This button takes us to the next screen to see the list
        btnViewList.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            // Sending all 4 parallel arrays to the next activity
            intent.putStringArrayListExtra("NAMES", itemNames)
            intent.putStringArrayListExtra("CATEGORIES", itemCategories)
            intent.putStringArrayListExtra("QUANTITIES", itemQuantities)
            intent.putStringArrayListExtra("COMMENTS", itemComments)
            startActivity(intent)
        }
    }
}
