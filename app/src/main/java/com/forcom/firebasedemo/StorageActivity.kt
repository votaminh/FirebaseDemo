package com.forcom.firebasedemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.ByteArrayOutputStream

class StorageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        btnUpload.setOnClickListener {
            val storage = Firebase.storage.reference.child("myImage.png")

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)

            val byte = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byte)

            val data = byte.toByteArray()

            val uploadTask = storage.putBytes(data)
            uploadTask.continueWithTask(object : Continuation<UploadTask.TaskSnapshot, Task<Uri>>{
                override fun then(p0: Task<UploadTask.TaskSnapshot>): Task<Uri> {
                    if(!p0.isSuccessful){
                        throw p0.exception!!;
                    }

                    return storage.downloadUrl
                }
            })
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val uri = it.getResult()
                        Toast.makeText(baseContext, "path : " + uri, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(baseContext, "fail", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
