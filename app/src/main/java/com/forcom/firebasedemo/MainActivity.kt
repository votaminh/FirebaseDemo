package com.forcom.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onclick()
    }

    private fun onclick() {

        fireStore.setOnClickListener{
            val intent = Intent(this, FireStoreActivity::class.java)
            startActivity(intent)
        }

        // ML Kit
        /**
         * is a mobile SDK that brings Google’s machine learning expertise to Android and iOS apps in a powerful yet easy-to-use package.
         */


        // Cloud Functions
        /**
         * Your code is stored in Google’s cloud and runs in a managed environment.
         * There’s no need to manage and scale your own servers. It’s supporting for Android, iOS, C++, Unity and Web Platform.
         */

        authentication.setOnClickListener{
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
        }

        // Hosting
        /**
         *  is production-grade web content hosting for developers
         */

        cloudStorage.setOnClickListener {
            val intent = Intent(this, StorageActivity::class.java)
            startActivity(intent)
        }

        realtimeDb.setOnClickListener{
            val intent = Intent(this, RealtimeDbActivity::class.java)
            startActivity(intent)
        }

        //In-App Messaging
        /**
         * Hiện dialog khi trong ứng đang chạy. ko có chạy ngầm
         */

        cloudMessage.setOnClickListener {
            val intent = Intent(this, CloudMessageActivity::class.java)
            startActivity(intent)
        }
    }
}
