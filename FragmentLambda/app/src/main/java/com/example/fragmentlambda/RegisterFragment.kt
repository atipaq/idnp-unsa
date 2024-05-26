package com.example.fragmentlambda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val edtUsername = view.findViewById<EditText>(R.id.edtUsername)
        val edtPassword = view.findViewById<EditText>(R.id.edtPassword)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val user = User(
                username = edtUsername.text.toString(),
                password = edtPassword.text.toString()
            )
            onUserRegistered(user)
        }

        return view
    }

    companion object {
        private lateinit var onUserRegistered: (User) -> Unit

        @JvmStatic
        fun newInstance(onUserRegistered: (User) -> Unit): RegisterFragment {
            this.onUserRegistered = onUserRegistered
            return RegisterFragment()
        }
    }
}
