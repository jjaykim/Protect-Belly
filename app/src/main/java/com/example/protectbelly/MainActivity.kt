package com.example.protectbelly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.protectbelly.auth.FirebaseUIActivity
import com.example.protectbelly.databinding.ActivityMainBinding
import com.example.protectbelly.models.Group
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
        lateinit var DB_GROUPS: ArrayList<Group>;
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
        initGetAllGroups();

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
        DB_GROUPS = ArrayList<Group>()
    }

    private fun initUserData() {

        db.collection("users").document(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener { document ->
                if(document.data != null) {
                    Log.d("ABC", "User Exists: ${document.data}");

                    currentUser.name = document.data!!["name"] as String?
                    currentUser.email = document.data!!["email"] as String?
                    currentUser.phoneNo = document.data!!["phoneNumber"] as String?
                    currentUser.age = document.data!!["age"] as Int?
                    currentUser.gender = document.data!!["gender"] as String?
                    currentUser.height = document.data!!["height"] as Int?
                    currentUser.weight = document.data!!["weight"] as Int?
                    currentUser.groups = ArrayList<String>()
//                    currentUser.groups = document.data!!["groups"] as ArrayList<String>
                    currentUser.profilePic = getRandomUserProfile((1..3).random());

                    Log.d("ABC", currentUser.profilePic.toString());

                } else {
                    db.collection("users").document(auth.currentUser?.uid.toString()).set(currentUser);
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ABC", "Get failed with", exception);
            };

//        db.collection("users").whereEqualTo("uid", auth.currentUser?.uid.toString())
//            .get()
//            .addOnCompleteListener { task ->
//                if(task.result.documents.size > 0) {
//                    task.result.documents[0].data?.map { currentUser }
//                    currentUser.documentId = task.result.documents[0].id
//                } else {
//                    db.collection("users").add(currentUser).addOnSuccessListener {
//                        task ->
//                        currentUser.documentId = task.id
//                    }.addOnFailureListener {
//                        Log.d("ABC", "Failed to add user")
//                    };
//                }
//        }
    }

    private fun getRandomUserProfile(randomInt: Int): Int {
        return when (randomInt) {
            1 -> R.drawable.ic_default_female_icon;
            2 -> R.drawable.ic_default_man_icon;
            3 -> R.drawable.ic_default_young_woman_icon;
            else -> R.drawable.ic_default_old_man_icon
        }
    }

    private fun initGetAllGroups() {
        db.collection("groups").get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val group:Group = Group();

                    group.documentId = document.id;
                    group.organizerId = document.data["organizerId"] as String
                    group.organizerName = document.data["organizerName"] as String
                    group.title = document.data["title"] as String
                    group.description = document.data["description"] as String
                    group.type = document.data["type"] as String
                    group.createdAt = document.data["createdAt"] as String
                    group.users = document.data["users"] as ArrayList<String>?

                    group.logo = getRandomLogo(document.data["logo"] as String)

                    DB_GROUPS.add(group)
                }

                Log.d("GET_GROUP_DEBUG", currentUser.toString())


            }
            .addOnFailureListener { exception ->
                Log.d("GET_GROUP_DEBUG", "Error getting documents: ", exception)
            }
    }

    private fun getRandomLogo(logo: String?): Int {
        return when (logo) {
            "ic_swimming_icon" -> R.drawable.ic_swimming_icon;
            "ic_lifting_icon" -> R.drawable.ic_lifting_icon;
            "ic_fitness_gym_icon" -> R.drawable.ic_fitness_gym_icon;
            "ic_running_icon" -> R.drawable.ic_running_icon;
            "ic_yoga_icon" -> R.drawable.ic_yoga_icon;
            else -> R.drawable.ic_random_group_logo;
        }
    }
}