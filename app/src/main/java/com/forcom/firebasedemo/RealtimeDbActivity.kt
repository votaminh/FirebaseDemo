package com.forcom.firebasedemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_realtime_db.*

class RealtimeDbActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference;
    var list = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_db)
        database = Firebase.database.reference

        btnAdd.setOnClickListener{
            val name = edName.text.toString()
            val age = edAge.text.toString()

            val user = User(name, age)
            database.child("user").push().setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "add success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(baseContext, "fail", Toast.LENGTH_LONG).show()
                }
        }

        database.child("user").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                list = ""
                for (child in p0.getChildren()) {
                    val name = child.child("name").value as String
                    val age = child.child("age").value as String

                    list += name + " : " + age + "\n"
                }

                tvList.text = list
            }
        })
    }
    data class User(
        var name: String? = "",
        var age: String? = ""
    )

}
