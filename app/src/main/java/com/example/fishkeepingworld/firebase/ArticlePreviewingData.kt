package com.example.fishkeepingworld.firebase

import android.util.Log
import com.example.fishkeepingworld.model.ArticlePreviewingModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ArticlePreviewingData {
    init{
        Log.i("Inside", "Yoo")
    }

    val articlePreviewingList: MutableList<ArticlePreviewingModel> = ArrayList()

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
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d("TAG", "Error getting documents: ", exception)
                                    }

//    val docList = Firebase.firestore.collection("articles")
//        .get()
//        .addOnSuccessListener { result ->
//            for (document in result) {
//                Log.d("TAG111", "${document.id} => ${document.data.get("title")}: " + result.size() )
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.d("TAG", "Error getting documents: ", exception)
//        }
//    fun getArticlePreviewingList
}

fun getPreheader(content: Map<String, String>): String? {
    if(content.containsKey("intro")) {
        return content.get("intro")
    }
    else {
        return ""
    }
}
