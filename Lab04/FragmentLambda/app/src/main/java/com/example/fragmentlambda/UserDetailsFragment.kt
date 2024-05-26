package com.example.fragmentlambda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_USER = "user"

class UserDetailsFragment : Fragment() {

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_details, container, false)
        val txtUserDetails = view.findViewById<TextView>(R.id.txtUserDetails)

        user?.let {
            txtUserDetails.text = "Username: ${it.username}\nPassword: ${it.password}"
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User?) =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
    }
}
