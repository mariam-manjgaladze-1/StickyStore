package com.example.finaluris_app.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.finaluris_app.MainActivityGame
import com.example.finaluris_app.R
import com.google.firebase.auth.FirebaseAuth

class FragmentLogin : Fragment(R.layout.fragment_login) {
    private lateinit var emailLogin : EditText
    private lateinit var passwordLogin : EditText
    private lateinit var userLoginBtn : Button
    private lateinit var userResetPass : TextView
    private lateinit var registerAcc : Button



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        emailLogin = view.findViewById(R.id.emailEdit)
        passwordLogin = view.findViewById(R.id.passwordEdit)
        userLoginBtn = view.findViewById(R.id.user_login_btn)
        userResetPass = view.findViewById(R.id.resetPassword)
        registerAcc = view.findViewById(R.id.register_btn)

        val navController = Navigation.findNavController(view)



        userResetPass.setOnClickListener{
            val reset = FragmentLoginDirections.actionFragmentLoginToFragmentReset()
            navController.navigate(reset)
        }


        registerAcc.setOnClickListener{
            val register = FragmentLoginDirections.actionFragmentLoginToFragmentRegister()
            navController.navigate(register)
        }




        userLoginBtn.setOnClickListener{
            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()


            if(email.isEmpty())  {
                Toast.makeText(this@FragmentLogin.requireActivity(), "input email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.isEmpty()) {
                Toast.makeText(this@FragmentLogin.requireActivity(), "input password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent (this@FragmentLogin.requireContext(), MainActivityGame::class.java)
                        startActivity(intent)

                    }
                    else {
                        Toast.makeText(this@FragmentLogin.requireActivity(), "there was error", Toast.LENGTH_SHORT).show()
                    }
                }}}}
