// Package and class declaration
package com.example.test

// This is the declaration of the ApodData class and represents an astronomy picture of the day (APOD) from NASA's API
data class ApodData(
    // APOD publication date
    val date: String,
    // APOD description
    val explanation: String,
    // URL to APOD high-definition image // val hdUrl: String,
    // APOD media type (e.g. "image", "video") // val hdUrl: String,val mediaType: String,
    // APOD title
    val title: String,
    // URL to APOD standard image
    val url: String
)





