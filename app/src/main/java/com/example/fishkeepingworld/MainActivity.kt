package com.example.fishkeepingworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val dbFkw = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        middleAppBar.setNavigationOnClickListener {
            dl.openDrawer(GravityCompat.START)
        }

        middleNavigation.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuStart -> {
                    // Handle favorite icon press
                    menuItem.isChecked = true
                    dl.closeDrawers()
                    true
                }
                R.id.menuFreshWaterFish -> {
                    // Handle search icon press
                    menuItem.isChecked = true
                    dl.closeDrawers()
                    true
                }
                R.id.menuSaltWaterFish -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Salt Water Fish", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menuKeeping -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Keeping & Breeding", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menuAquarium -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Aquarium", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menuBook -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Book", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menuBlog -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Blog", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menuFb -> {
                    // Handle more item (inside overflow menu) press
                    Toast.makeText(this, "Facebook", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }
}