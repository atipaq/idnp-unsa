package com.example.lab09

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.lab09.viewmodel.ProgressViewModel

class MyService : Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var progress = 0
    private lateinit var progressViewModel: ProgressViewModel

    override fun onCreate() {
        super.onCreate()
        handler = Handler(mainLooper)
        progressViewModel = ViewModelProvider(applicationContext as ViewModelStoreOwner).get(ProgressViewModel::class.java)

        runnable = object : Runnable {
            override fun run() {
                progressViewModel.setProgress(progress)

                // Simula el progreso del audio
                progress++
                if (progress <= 100) {
                    handler.postDelayed(this, 1000)
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
