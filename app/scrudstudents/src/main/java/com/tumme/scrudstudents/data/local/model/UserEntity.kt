package com.tumme.scrudstudents.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Represents a student in the database.
@Entity(tableName = "users")
data class UserEntity(
    // The student's unique ID.
    @PrimaryKey val idUser: Int,
    // The student's last name.
    val lastName: String,
    // The student's first name.
    val firstName: String,
    // The student's date of birth.
    val dateOfBirth: Date,
    // The student's gender.
    val gender: Gender,
    val role: Role
)
