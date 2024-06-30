package com.example.hangmangame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var canvasView: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        canvasView = findViewById(R.id.canvasView)

        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            canvasView.nextPart()
        }

        findViewById<Button>(R.id.btnReset).setOnClickListener {
            canvasView.reset()
        }
    }
}
