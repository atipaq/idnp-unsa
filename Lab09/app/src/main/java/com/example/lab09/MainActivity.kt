package com.example.lab09

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lab09.viewmodel.ProgressViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private val progressViewModel: ProgressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        progressViewModel.progress.observe(this, Observer { progress ->
            progressBar.progress = progress ?: 0
        })

        // Iniciar el servicio
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }
}
