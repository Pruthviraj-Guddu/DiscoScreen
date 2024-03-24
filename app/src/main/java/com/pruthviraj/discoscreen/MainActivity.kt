package com.pruthviraj.discoscreen

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

class MainActivity : AppCompatActivity() {
    private var isPartyModeOn = false
    private lateinit var rootView: View
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, insets ->
            rootView.setPadding(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
            insets
        }

        findViewById<View>(R.id.partyButton).setOnClickListener {
            togglePartyMode()
        }
    }

    private fun togglePartyMode() {
        isPartyModeOn = !isPartyModeOn
        if (isPartyModeOn) {
            startFlashingLights()
        } else {
            stopFlashingLights()
        }
    }

    private fun startFlashingLights() {
        handler.post(object : Runnable {
            var colorIndex = 0
            val colors = arrayOf(
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.CYAN,
                Color.MAGENTA,
                Color.WHITE
            ) // Add more colors as needed
            override fun run() {
                if (isPartyModeOn) {
                    rootView.setBackgroundColor(colors[colorIndex])
                    colorIndex = (colorIndex + 1) % colors.size
                    handler.postDelayed(this, 500) // Change flashing speed here
                }
            }
        })
    }


    private fun stopFlashingLights() {
        handler.removeCallbacksAndMessages(null)
        rootView.setBackgroundColor(Color.WHITE)
    }
}
