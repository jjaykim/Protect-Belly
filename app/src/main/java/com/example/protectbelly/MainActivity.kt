package com.example.protectbelly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.protectbelly.auth.FirebaseUIActivity
import com.example.protectbelly.databinding.ActivityMainBinding
import com.example.protectbelly.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), FirebaseAuth.AuthStateListener {

    private lateinit var binding: ActivityMainBinding;
    private val db = Firebase.firestore;
    private val auth = FirebaseAuth.getInstance();
    companion object{
        lateinit var currentUser:User;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment;

        var navController = navHostFragment.navController;

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);



        initUser();
        initUserData();

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

    private fun initUser() {

        currentUser = User()
        currentUser.name = auth.currentUser?.displayName
        currentUser.email = auth.currentUser?.email
        currentUser.phoneNo = auth.currentUser?.phoneNumber
    }

    private fun initUserData() {

        db.collection("users").document(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener { document ->
                if(document.data != null) {
                    document.data?.map { currentUser}

                } else {
                    db.collection("users").document(auth.currentUser?.uid.toString()).set(currentUser);
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ABC", "Get failed with", exception);
            };

    }

}