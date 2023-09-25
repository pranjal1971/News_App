package com.deva.newsapp

data class newsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)