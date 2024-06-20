package com.example.firebase1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase1.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textview.setOnClickListener{
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()


            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if(pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,SignIn::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText( this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText( this, "Password do not match",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText( this, "Empty fields !!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}