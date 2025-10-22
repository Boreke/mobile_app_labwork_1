package com.tumme.scrudstudents.data.local.dao

import androidx.room.*
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscribeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subscribe: SubscribeEntity)

    @Delete
    suspend fun delete(subscribe: SubscribeEntity)

    @Query("SELECT * FROM subscribes")
    fun getAllSubscribes(): Flow<List<SubscribeEntity>>

    @Query("SELECT * FROM subscribes WHERE idStudent = :sId")
    fun getSubscribesByStudent(sId: Int): Flow<List<SubscribeEntity>>

    @Query("SELECT * FROM subscribes WHERE idCourse = :cId")
    fun getSubscribesByCourse(cId: Int): Flow<List<SubscribeEntity>>

    @Query("""
        SELECT 
            s.idStudent, 
            s.idCourse, 
            st.firstName AS studentFirstName, 
            st.lastName AS studentLastName, 
            c.nameCourse AS courseName, 
            s.score
        FROM subscribes s
        INNER JOIN students st ON s.idStudent = st.idStudent
        INNER JOIN courses c ON s.idCourse = c.idCourse
    """)
    fun getAllSubscriptionsWithDetails(): Flow<List<SubscriptionWithDetails>>
}
