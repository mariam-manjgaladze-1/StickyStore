package com.example.finaluris_app.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.finaluris_app.R
import com.google.firebase.auth.FirebaseAuth

class FragmentRegister : Fragment(R.layout.fragment_register) {

    private lateinit var registerName : EditText
    private lateinit var registerEmail : EditText
    private lateinit var registerPasswordUser : EditText
    private lateinit var confirmPassword : EditText
    private lateinit var registerUser : Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerName = view.findViewById(R.id.registerName)
        registerEmail = view.findViewById(R.id.registerEmail)
        registerPasswordUser = view.findViewById(R.id.registerPassword)
        confirmPassword = view.findViewById(R.id.register_confirm_password)
        registerUser = view.findViewById(R.id.user_register_btn)
        val navController = Navigation.findNavController(view)






        registerUser.setOnClickListener{

            val email = registerEmail.text.toString()
            val password = registerPasswordUser.text.toString()
            val password2 = confirmPassword.text.toString()
            val name = registerName.text.toString()


            if (name.isEmpty()) {
                Toast.makeText(this@FragmentRegister.requireActivity(), "please input Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(email.isEmpty()) {
                Toast.makeText(this@FragmentRegister.requireActivity(), "please input mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length < 6) {
                Toast.makeText(
                    this@FragmentRegister.requireActivity(),
                    "please input valid password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password != password2) {
                Toast.makeText(this@FragmentRegister.requireActivity(), "password doesn't match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this@FragmentRegister.requireActivity(), "registration successful", Toast.LENGTH_SHORT).show()
                        val login = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
                        navController.navigate(login)

                    }
                    else{
                        Toast.makeText(this@FragmentRegister.requireActivity(), "there was an error", Toast.LENGTH_SHORT).show()
                    }

                }}}}