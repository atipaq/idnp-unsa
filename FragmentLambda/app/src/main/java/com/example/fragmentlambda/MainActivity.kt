package com.example.fragmentlambda

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    private lateinit var txtMensaje: TextView
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtMensaje = findViewById(R.id.txtMensaje)
        val btnShowRegister: Button = findViewById(R.id.btnShowRegister)
        val btnShowUserDetails: Button = findViewById(R.id.btnShowUserDetails)

        btnShowRegister.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance { user ->
                currentUser = user
                txtMensaje.text = "User Registered: ${user.username}"
            }
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.frameLayout, registerFragment)
            }
        }

        btnShowUserDetails.setOnClickListener {
            val userDetailsFragment = UserDetailsFragment.newInstance(currentUser)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.frameLayout, userDetailsFragment)
            }
        }
    }
}
