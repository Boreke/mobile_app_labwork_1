package com.tumme.scrudstudents.data.local.dao

import androidx.room.*
import com.tumme.scrudstudents.data.local.model.StudentEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the Student entity.
 * This interface defines the database interactions for the "students" table.
 * Room will generate an implementation of this interface at compile time.
 */
@Dao
interface StudentDao {
    /**
     * Retrieves all students from the database, ordered by last name and then first name.
     * This function returns a Flow, which allows the UI to observe changes to the student data automatically.
     * When the data in the "students" table changes, the Flow will emit a new list of students.
     * @return A Flow that emits a list of [StudentEntity] objects.
     */
    @Query("SELECT * FROM students ORDER BY lastName, firstName")
    fun getAllStudents(): Flow<List<StudentEntity>>

    /**
     * Inserts a new student into the database or replaces an existing one.
     * The `onConflict` strategy is set to `REPLACE`, so if a student with the same primary key already exists, it will be updated.
     * This is a suspend function, meaning it must be called from a coroutine or another suspend function.
     * @param student The [StudentEntity] to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    /**
     * Deletes a student from the database.
     * This is a suspend function.
     * @param student The [StudentEntity] to be deleted.
     */
    @Delete
    suspend fun delete(student: StudentEntity)

    /**
     * Retrieves a single student from the database by their ID.
     * This is a suspend function.
     * @param id The ID of the student to retrieve.
     * @return The [StudentEntity] with the given ID, or null if no such student exists.
     */
    @Query("SELECT * FROM students WHERE idStudent = :id LIMIT 1")
    suspend fun getStudentById(id: Int): StudentEntity?
}
