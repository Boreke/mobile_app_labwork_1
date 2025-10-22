package com.tumme.scrudstudents.di

import android.content.Context
import androidx.room.Room
import com.tumme.scrudstudents.data.local.AppDatabase
import com.tumme.scrudstudents.data.local.dao.CourseDao
import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.dao.SubscribeDao
import com.tumme.scrudstudents.data.repository.CourseRepository
import com.tumme.scrudstudents.data.repository.StudentRepository
import com.tumme.scrudstudents.data.repository.SubscriptionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "scrud-db")
            // Allow Room to destructively recreate database tables if Migrations are not provided.
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideStudentDao(db: AppDatabase): UserDao = db.userDao()
    @Provides fun provideCourseDao(db: AppDatabase): CourseDao = db.courseDao()
    @Provides fun provideSubscribeDao(db: AppDatabase): SubscribeDao = db.subscribeDao()

    @Provides
    @Singleton
    fun provideStudentRepository(userDao: UserDao): StudentRepository =
        StudentRepository(userDao)
    @Provides
    @Singleton
    fun provideCourseRepository(courseDao: CourseDao): CourseRepository =
        CourseRepository(courseDao)
    @Provides
    @Singleton
    fun provideSubscribeRepository(subscribeDao: SubscribeDao): SubscriptionRepository =
        SubscriptionRepository(subscribeDao)
}
