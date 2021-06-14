package com.example.fishkeepingworld.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishkeepingworld.R
import com.example.fishkeepingworld.adapters.ArticleAdapter
import com.example.fishkeepingworld.firebase.ArticlePreviewingData
import com.example.fishkeepingworld.firebase.getPreheader
import com.example.fishkeepingworld.model.ArticlePreviewingModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val articlePreviewingList: MutableList<ArticlePreviewingModel> = ArrayList()

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

//        val apd = ArticlePreviewingData()
//        apd.docList
//        val articleList = apd.articlePreviewingList
//        Log.i("Yooooooo: ","Hello Motherfucker: " + articleList.size + " and " + apd)
//        for(a in articleList) {
//            Log.i("Heyyy: ","Hello: " + a)
//        }

        val docList = Firebase.firestore.collection("articles")
            .get()
            .addOnSuccessListener {documents ->
                for(d in documents) {
                    val ph = getPreheader(d.data.get("content") as Map<String, String>)
                    val newArc = ArticlePreviewingModel(
                        "${d.data.get("image")}",
                        "${d.data.get("title")}",
                        ph.toString()
                    )

                    articlePreviewingList.add(newArc)
                    Log.d("Inside Firestore", "hey: ${d.data.get("title")}" + ph.toString() + " " + articlePreviewingList.size)
                }
                Log.d("Outside of for loop", "ArticlePreviewingList size: " + articlePreviewingList.size)
                // Create adapter passing in the sample user data
                val articleAdapter = ArticleAdapter(articlePreviewingList)
                // Attach the adapter to the recyclerview to populate items
                rvArticles.adapter = articleAdapter
                // Set layout manager to position the items
                rvArticles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }



//        // Create adapter passing in the sample user data
//        val articleAdapter = ArticleAdapter(articlePreviewingList)
//        // Attach the adapter to the recyclerview to populate items
//        rvArticles.adapter = articleAdapter
//        // Set layout manager to position the items
//        rvArticles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun getPreheader(content: Map<String, String>): String? {
        if(content.containsKey("intro")) {
            return content.get("intro")
        }
        else {
            return ""
        }
    }
}