package com.example.cruditemapp.data

data class Item(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    // Construtor vazio necessário para o Realtime Database
    constructor() : this("", "", "", 0L)
} 