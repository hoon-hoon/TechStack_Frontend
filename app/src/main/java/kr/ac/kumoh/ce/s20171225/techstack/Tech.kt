package kr.ac.kumoh.ce.s20171225.techstack

data class Tech(
    val id: Int,
    val name: String,
    val description: String,
    val usage_purpose: String,
    val image_url: String,
    val category: String,
    val difficulty: Int,
    val trend_index: Int,
    val official_website: String,
)