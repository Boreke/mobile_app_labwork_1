package com.tumme.scrudstudents.data.local.model

import androidx.room.ColumnInfo

/**
 * A data class to hold the result of a join query on the subscribes, students, and courses tables.
 * This is a read-only class used to display subscription information with human-readable names.
 * The property names must match the column names or aliases returned by the DAO query.
 */
data class SubscriptionWithDetails(
    val idStudent: Int,
    val idCourse: Int,
    @ColumnInfo(name = "studentFirstName") val studentFirstName: String,
    @ColumnInfo(name = "studentLastName") val studentLastName: String,
    @ColumnInfo(name = "courseName") val courseName: String,
    val score: Float
)
