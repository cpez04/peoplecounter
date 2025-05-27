package com.example.peoplecounter

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private var currentCount = 0
    private var totalCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("PeopleCounterPrefs", Context.MODE_PRIVATE)
        currentCount = prefs.getInt("currentCount", 0)
        totalCount = prefs.getInt("totalCount", 0)

        val currentCountView = findViewById<TextView>(R.id.currentCount)
        val totalCountView   = findViewById<TextView>(R.id.totalCount)
        val increaseButton   = findViewById<Button>(R.id.increaseButton)
        val decreaseButton   = findViewById<Button>(R.id.decreaseButton)
        val resetButton      = findViewById<Button>(R.id.resetButton)

        fun updateUI() {
            // update texts
            currentCountView.text = "$currentCount People"
            totalCountView.text   = "Total: $totalCount"

            // If we hit over capacity
            currentCountView.setTextColor(
                if (currentCount > 15) Color.RED
                else Color.BLACK
            )

            // If we <= 0, we don't show the - button
            decreaseButton.visibility =
                if (currentCount > 0) View.VISIBLE
                else View.GONE
        }

        updateUI()

        increaseButton.setOnClickListener {
            currentCount++
            totalCount++
            updateUI()
        }

        decreaseButton.setOnClickListener {
            if (currentCount > 0) {
                currentCount--
                updateUI()
            }
        }

        resetButton.setOnClickListener {
            currentCount = 0
            totalCount   = 0
            updateUI()
        }
    }

    override fun onPause() {
        super.onPause()
        getSharedPreferences("PeopleCounterPrefs", Context.MODE_PRIVATE).edit {
            putInt("currentCount", currentCount)
            putInt("totalCount", totalCount)
        }
    }
}
