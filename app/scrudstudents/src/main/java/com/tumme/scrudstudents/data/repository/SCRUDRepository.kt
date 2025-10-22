package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.CourseDao
import com.tumme.scrudstudents.data.local.dao.StudentDao
import com.tumme.scrudstudents.data.local.dao.SubscribeDao
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides a clean API for data access to the rest of the application.
 * It abstracts the data sources (in this case, the DAOs) from the ViewModels.
 * This class is the single source of truth for the app's data.
 *
 * @param studentDao The DAO for student-related database operations.
 * @param courseDao The DAO for course-related database operations.
 * @param subscribeDao The DAO for subscription-related database operations.
 */
class SCRUDRepository(
    private val studentDao: StudentDao,
    private val courseDao: CourseDao,
    private val subscribeDao: SubscribeDao
) {
    // region Student-related operations

    /** Returns a Flow that emits a list of all students. */
    fun getAllStudents(): Flow<List<StudentEntity>> = studentDao.getAllStudents()

    /** Inserts or updates a student in the database. */
    suspend fun insertStudent(student: StudentEntity) = studentDao.insert(student)

    /** Deletes a student from the database. */
    suspend fun deleteStudent(student: StudentEntity) = studentDao.delete(student)

    /** Retrieves a single student by their ID. */
    suspend fun getStudentById(id: Int) = studentDao.getStudentById(id)

    // endregion

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

    // region Subscription-related operations

    /** Returns a Flow that emits a list of all subscriptions with student and course names. */
    fun getAllSubscriptionsWithDetails(): Flow<List<SubscriptionWithDetails>> = subscribeDao.getAllSubscriptionsWithDetails()

    /** Returns a Flow that emits a list of all subscriptions. */
    fun getAllSubscribes(): Flow<List<SubscribeEntity>> = subscribeDao.getAllSubscribes()

    /** Returns a Flow that emits a list of subscriptions for a given student. */
    fun getSubscribesByStudent(sId: Int): Flow<List<SubscribeEntity>> = subscribeDao.getSubscribesByStudent(sId)

    /** Returns a Flow that emits a list of subscriptions for a given course. */
    fun getSubscribesByCourse(cId: Int): Flow<List<SubscribeEntity>> = subscribeDao.getSubscribesByCourse(cId)

    /** Inserts a new subscription. */
    suspend fun insertSubscribe(subscribe: SubscribeEntity) = subscribeDao.insert(subscribe)

    /** Deletes a subscription. */
    suspend fun deleteSubscribe(subscribe: SubscribeEntity) = subscribeDao.delete(subscribe)

    // endregion
}
