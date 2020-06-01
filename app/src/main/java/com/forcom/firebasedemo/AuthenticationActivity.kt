package com.forcom.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            val email = edEmail.text.toString()
            val pass = edPass.text.toString()

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        if(!auth.currentUser?.isEmailVerified!!){
                            Toast.makeText(baseContext, "account had not verify", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(baseContext, "Login success", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(baseContext, "Fail", Toast.LENGTH_LONG).show()
                    }
                }
        }
        btnRegister.setOnClickListener{
            val email = edEmail.text.toString()
            val pass = edPass.text.toString()

            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                            // Đăng kí tài khoản thành công, gửi verify
                        auth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener{
                                if(it.isSuccessful){
                                        // gửi verify
                                    auth.currentUser?.sendEmailVerification()
                                        ?.addOnCompleteListener{
                                            if(it.isSuccessful){
                                                Toast.makeText(baseContext, "a mail was sent to your email, please check to verify", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                }else{
                                    Toast.makeText(baseContext, "Fail", Toast.LENGTH_LONG).show()
                                }
                            }

                    }else{
                        Toast.makeText(baseContext,"Fail", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
