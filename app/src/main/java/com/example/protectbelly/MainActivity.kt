package com.example.protectbelly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.protectbelly.auth.FirebaseUIActivity
import com.example.protectbelly.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), FirebaseAuth.AuthStateListener {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment;

        var navController = navHostFragment.navController;

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);

//        val navHostFragment = supportFragmentManager.findFragmentById();

//        var auth = FirebaseAuth.getInstance();


//        var users = HashMap<String, String>();
//        users.put("firstName", "Yuv");
//        users.put("lastName", "parmar");
//
//        var db = Firebase.firestore;
//
//        db.collection("users").add(users).addOnSuccessListener {documentReference ->
//            Log.d("abc", "success");
//        }.addOnFailureListener {
//            documentReference ->
//            Log.d("abc", "failure");
//        }

    }

    override fun onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    override fun onAuthStateChanged(p0: FirebaseAuth) {
        if (p0.currentUser == null) {
            var intent = Intent(this, FirebaseUIActivity::class.java)
            startActivity(intent);
            finish()
        }
    }

}