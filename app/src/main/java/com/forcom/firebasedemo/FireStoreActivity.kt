package com.forcom.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_fire_store.*

/***
 * - nosql
 * - k có realtime database
 * - nhanh hơn, nhẹ hơn realtime
 */
class FireStoreActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_store)

        btnAdd.setOnClickListener{
            val user = User(edName.text.toString(),edAge.text.toString())
            db.collection("user")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
                }
        }
        btnFecth.setOnClickListener{
            getData()
        }
        getData()
    }

    private fun getData() {
            db.collection("user")
                .get()
                .addOnSuccessListener {
                    var list = ""
                    for(document in it){
                        list += "${document.id} => ${document.data}" + "\n"
                    }

                    tvList.text = list
                }
                .addOnFailureListener{
                    Log.i("lalala", "Error getting documents.", it)
                }
    }

    data class User(
        val name: String,
        val age: String
    )
}
