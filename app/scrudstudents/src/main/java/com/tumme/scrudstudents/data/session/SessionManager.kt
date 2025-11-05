package com.tumme.scrudstudents.data.session

import android.content.Context
import android.content.SharedPreferences
import com.tumme.scrudstudents.data.local.model.Role
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages user session persistence using SharedPreferences.
 * This class is a singleton provided by Hilt.
 */
@Singleton
class SessionManager @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_session_prefs"
        private const val KEY_USER_ID = "user_id"

        private const val USER_ROLE= "user_role"
        private const val NO_USER = -1
    }

    /**
     * Saves a user ID to start a session.
     */
    fun saveSession(userId: Int, userRole: Role) {
        prefs.edit().putInt(KEY_USER_ID, userId).apply()
        prefs.edit().putString(USER_ROLE, userRole.name).apply()
    }

    /**
     * Retrieves the active user ID.
     * @return The user ID if logged in, otherwise -1.
     */
    fun getUserId(): Int {
        return prefs.getInt(KEY_USER_ID, NO_USER)
    }
    fun getUserRole(): Role? {
        val roleName = prefs.getString(USER_ROLE, null) ?: return null
        // Try strict enum lookup first (expects names like "Student").
        return try {
            Role.valueOf(roleName)
        } catch (e: Exception) {
            // Fallback: try to resolve by the stored role 'value' (e.g. "student")
            // Role.from handles the lowercase/internal representation.
            Role.from(roleName.lowercase())
                // As a final fallback, match ignoring case against enum names.
                ?: Role.values().firstOrNull { it.name.equals(roleName, ignoreCase = true) }
        }
    }
    /**
     * Checks if a user is currently logged in.
     */
    fun isLoggedIn(): Boolean {
        return getUserId() != NO_USER
    }

    /**
     * Clears session data to log the user out.
     */
    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
