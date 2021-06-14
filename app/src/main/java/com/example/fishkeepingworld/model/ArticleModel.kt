package com.example.fishkeepingworld.model

data class ArticleModel(val image: String = "",
                        val category: String = "",
                        val title: String = "",
                        val content: Content,
                        val url: String = "")