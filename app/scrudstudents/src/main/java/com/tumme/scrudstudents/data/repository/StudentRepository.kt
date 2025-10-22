package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides a clean API for data access to the rest of the application.
 * It abstracts the data sources (in this case, the DAOs) from the ViewModels.
 * This class is the single source of truth for the app's data.
 *
 * @param userDao The DAO for student-related database operations.
 * @param courseDao The DAO for course-related database operations.
 * @param subscribeDao The DAO for subscription-related database operations.
 */
class StudentRepository(
    private val userDao: UserDao
) {
    // region Student-related operations

    /** Returns a Flow that emits a list of all students. */
    fun getAllStudents(): Flow<List<UserEntity>> = userDao.getAllStudents()

    /** Inserts or updates a student in the database. */
    suspend fun insertStudent(student: UserEntity) = userDao.insert(student)

    /** Deletes a student from the database. */
    suspend fun deleteStudent(student: UserEntity) = userDao.delete(student)

    /** Retrieves a single student by their ID. */
    suspend fun getStudentById(id: Int) = userDao.getUserById(id)

    // endregion
}
