package com.example.protectbelly

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var users = HashMap<String, String>();
        users.put("firstName", "Yuv");
        users.put("lastName", "parmar");

        var db = Firebase.firestore;

        db.collection("users").add(users).addOnSuccessListener {documentReference ->
            Log.d("abc", "success");
        }.addOnFailureListener {
            documentReference ->
            Log.d("abc", "failure");
        }

    }
}