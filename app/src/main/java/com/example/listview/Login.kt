package com.example.listview

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var database = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        myRef = database.getReference("users")
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.getCurrentUser()

        if (currentUser != null) {
            startNewActivity()
        }
    }

    fun startNewActivity() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun bt_clicked (view: View) {
        mAuth!!.createUserWithEmailAndPassword(Mail.text.toString(), Pass.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                val user = mAuth!!.getCurrentUser()
                myRef!!.child(user!!.uid).setValue(user!!.email.toString())

                var udata = UserData()
                with(udata) {
                    name = Nombre.text.toString()
                    age = Edad.text.toString()
                    email = Mail.text.toString()
                    password = Pass.text.toString()
                }
                myRef!!.child(user!!.uid).push().setValue(udata)

                Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show()
                startNewActivity()
            } else { // If sign in fails, display a message to the user.
                Toast.makeText(this, "Registro fallido...", Toast.LENGTH_SHORT).show()
            }
            // ...
        }
    }
}
