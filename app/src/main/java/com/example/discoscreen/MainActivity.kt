package com.example.discoscreen

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
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
            var isWhite = true
            override fun run() {
                if (isPartyModeOn) {
                    if (isWhite) {
                        rootView.setBackgroundColor(Color.WHITE)
                    } else {
                        rootView.setBackgroundColor(Color.BLACK)
                    }
                    isWhite = !isWhite
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
