package com.tumme.scrudstudents.data.local.dao

import androidx.room.*
import com.tumme.scrudstudents.data.local.model.Role
import com.tumme.scrudstudents.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE role = 'student' ORDER BY lastName, firstName")
    fun getAllStudents(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE role = 'teacher' ORDER BY lastName, firstName")
    fun getAllTeachers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE role = 'admin' ORDER BY lastName, firstName")
    fun getAllAdmins(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users  ORDER BY lastName, firstName")
    fun getAllUsers(): Flow<List<UserEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: UserEntity){
        val studentWithRole = student.copy(role = Role.Student);
        insert(studentWithRole)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: UserEntity){
        val teacherWithRole = teacher.copy(role = Role.Teacher);
        insert(teacherWithRole)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdmin(admin: UserEntity){
        val adminWithRole = admin.copy(role = Role.Admin);
        insert(adminWithRole)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(student: UserEntity)

    @Query("SELECT * FROM users WHERE idUser = :id LIMIT 1")
    suspend fun getUserById(id: Int): UserEntity?

    @Query("SELECT * FROM users WHERE firstname = :firstName AND lastname = :lastName LIMIT 1")
    suspend fun getUserByName(firstName: String, lastName: String): UserEntity?

}
