package com.example.finaluris_app.game.notifications

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finaluris_app.MainActivity
import com.example.finaluris_app.R
import com.example.finaluris_app.databinding.FragmentNotificationsBinding
import com.example.finaluris_app.fragments.FragmentRegisterDirections
import com.google.firebase.auth.FirebaseAuth

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {
    private lateinit var changeFragment : Button
    private lateinit var logOutAcc : Button
    private lateinit var btnshop : Button
    private lateinit var btncontacts : Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeFragment = view.findViewById(R.id.button)
        logOutAcc= view.findViewById(R.id.button2)
        btnshop= view.findViewById(R.id.shop_btn)
        btncontacts= view.findViewById(R.id.contact)

        val navController = Navigation.findNavController(view)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()




        changeFragment.setOnClickListener {
            val change = NotificationsFragmentDirections.actionNavigationNotificationsToFragmentChange()
            navController.navigate(change)

        }


        logOutAcc.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@NotificationsFragment.requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        btnshop.setOnClickListener {
            val website = "https://www.etsy.com/market/sticker_shops"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(website)
            startActivity(intent)
        }

        btncontacts.setOnClickListener {
            val contact = NotificationsFragmentDirections.actionNavigationNotificationsToFragmentContacts()
            navController.navigate(contact)
        }
    }
}



