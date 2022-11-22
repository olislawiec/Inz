package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.screens.calendar.calendar
import com.example.myapplication.screens.home.home
import com.example.myapplication.screens.recipebook.recipebook
import com.example.myapplication.screens.search.search
import com.example.myapplication.screens.shopping.shopping
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Recipes").document("eKhLdy0kftLNOayFDLp2")
        db.collection("Recipes")
            //.whereEqualTo("capital", true)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("KUPA1", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("KUPA1", "Error getting documents: ", exception)
            }





        setContentView(binding.root)
        replaceFragment(home())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(home())
                R.id.search -> replaceFragment(search())
                R.id.my_recipes -> replaceFragment(recipebook())
                R.id.calendar -> replaceFragment(calendar())
                R.id.shopping -> replaceFragment(shopping())

            else ->{


        }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}