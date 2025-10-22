package com.tumme.scrudstudents.data.local.model

import androidx.room.ColumnInfo

/**
 * A data class to hold the result of a join query on the subscribes, students, and courses tables.
 * This is a read-only class used to display subscription information with human-readable names.
 * The property names must match the column names or aliases returned by the DAO query.
 */
data class SubscriptionWithDetails(
    val idUser: Int,
    val idCourse: Int,
    @ColumnInfo(name = "userFirstName") val userFirstName: String,
    @ColumnInfo(name = "userLastName") val userLastName: String,
    @ColumnInfo(name = "courseName") val courseName: String,
    val score: Float
)
