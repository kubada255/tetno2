
package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wiek: Int,
    val wzrost: Int,
    val tetno: Int,
    val cistolic: Int,
    val diastolic: Int,
    val waga: Int,
    val data: Long
)
