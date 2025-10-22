package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.CourseDao
import com.tumme.scrudstudents.data.local.model.CourseEntity
import kotlinx.coroutines.flow.Flow

class CourseRepository(
    private val courseDao: CourseDao
) {
    // region Course-related operations

    /** Returns a Flow that emits a list of all courses. */
    fun getAllCourses(): Flow<List<CourseEntity>> = courseDao.getAllCourses()

    /** Inserts or updates a course in the database. */
    suspend fun insertCourse(course: CourseEntity) = courseDao.insert(course)

    /** Deletes a course from the database. */
    suspend fun deleteCourse(course: CourseEntity) = courseDao.delete(course)

    /** Retrieves a single course by its ID. */
    suspend fun getCourseById(id: Int) = courseDao.getCourseById(id)

    // endregion
}