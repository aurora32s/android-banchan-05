package com.seom.banchan.data.api.response

data class Category(
    val id: String,
    val name: String,
    val items: List<Menu>
)