package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.SubscribeDao
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails
import kotlinx.coroutines.flow.Flow

class SubscriptionRepository (
    private val subscribeDao: SubscribeDao
) {
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

}