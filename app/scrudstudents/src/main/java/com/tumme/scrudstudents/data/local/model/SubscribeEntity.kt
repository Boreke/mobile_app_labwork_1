package com.tumme.scrudstudents.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Represents the subscription of a Student to a Course, including the score.
 * This entity establishes a many-to-many relationship between Students and Courses.
 *
 * @param idStudent The ID of the student, acting as a foreign key to the 'students' table.
 * @param idCourse The ID of the course, acting as a foreign key to the 'courses' table.
 * @param score The score obtained by the student in the course.
 */
@Entity(
    tableName = "subscribes",
    // A student can only be subscribed to a specific course once.
    primaryKeys = ["idStudent", "idCourse"],
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["idStudent"],
            childColumns = ["idStudent"],
            onDelete = ForeignKey.CASCADE // If a student is deleted, their subscriptions are also deleted.
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["idCourse"],
            childColumns = ["idCourse"],
            onDelete = ForeignKey.CASCADE // If a course is deleted, its subscriptions are also deleted.
        )
    ]
)
data class SubscribeEntity(
    val idStudent: Int,
    val idCourse: Int,
    val score: Float
)
