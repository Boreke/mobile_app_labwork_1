package com.tumme.scrudstudents.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tumme.scrudstudents.data.session.SessionManager
import com.tumme.scrudstudents.ui.HomePageScreen
import com.tumme.scrudstudents.ui.auth.LoginScreen
import com.tumme.scrudstudents.ui.auth.SignUpScreen
import com.tumme.scrudstudents.ui.course.CourseFormScreen
import com.tumme.scrudstudents.ui.course.CourseListScreen
import com.tumme.scrudstudents.ui.student.StudentDetailScreen
import com.tumme.scrudstudents.ui.student.StudentFormScreen
import com.tumme.scrudstudents.ui.student.StudentListScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeFormScreen
import com.tumme.scrudstudents.ui.subscribe.SubscribeListScreen
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

object Routes {
    const val STUDENT_LIST = "student_list"
    const val STUDENT_FORM = "student_form"

    const val COURSE_LIST = "course_list"
    const val COURSE_FORM = "course_form"

    const val SUBSCRIBE_LIST = "subscribe_list"
    const val SUBSCRIBE_FORM = "subscribe_form"

    const val LOGIN = "login"

    const val SIGNUP = "signup"

    const val HOME = "home"

}
@EntryPoint
@InstallIn(SingletonComponent::class)
interface SessionManagerEntryPoint {
    fun sessionManager(): SessionManager
}
@Composable
fun AppNavHost() {
    val context = LocalContext.current
    val sessionManager = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext as Application,
            SessionManagerEntryPoint::class.java
        ).sessionManager()
    }
    val navController = rememberNavController()
    // Determine the start destination based on login status.
    val startDestination = if (sessionManager.isLoggedIn()) Routes.HOME else Routes.LOGIN

    NavHost(navController, startDestination = startDestination) {
        composable(Routes.HOME){
            HomePageScreen(
                onNavigateToCourses =  { navController.navigate(Routes.COURSE_LIST) },
                onNavigateToStudents = { navController.navigate(Routes.STUDENT_LIST) },
                onNavigateToSubscriptions = { navController.navigate(Routes.SUBSCRIBE_LIST) },
                onLogout = {
                    // Clear session and navigate to login, removing Home from back stack
                    sessionManager.clearSession()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                sessionManager = sessionManager,
                onNavigateToForm = { navController.navigate(Routes.STUDENT_FORM) },
                onNavigateToDetail = { id -> navController.navigate("student_detail/$id") }
            )
        }
        composable(Routes.STUDENT_FORM) {
            StudentFormScreen(onSaved = { navController.popBackStack() })
        }
        composable(
            "student_detail/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("studentId") ?: 0
            StudentDetailScreen(studentId = id, onBack = { navController.popBackStack() })
        }
        composable(Routes.COURSE_LIST) {
            CourseListScreen(
                sessionManager = sessionManager,
                onNavigateToForm = { navController.navigate(Routes.COURSE_FORM) }
            )
        }
        composable(Routes.COURSE_FORM) {
            CourseFormScreen(onSaved = { navController.popBackStack() })
        }
        composable(Routes.SUBSCRIBE_LIST) {
            SubscribeListScreen(
                sessionManager= sessionManager,
                onNavigateToForm = { navController.navigate(Routes.SUBSCRIBE_FORM) }
            )
        }
        composable(Routes.SUBSCRIBE_FORM) {
            SubscribeFormScreen(onSaved = { navController.popBackStack() })
        }
        composable (Routes.LOGIN){
            LoginScreen(
                onNavigateToSignUpScreen = { navController.navigate(Routes.SIGNUP) },
                onLoginSuccess = { navController.navigate(Routes.HOME) },
            )
        }
        composable(Routes.SIGNUP){
            SignUpScreen(
                onNavigateToLoginScreen = { navController.navigate(Routes.LOGIN) },
                onSignUpSuccess = { navController.navigate(Routes.HOME) }
            )
        }
    }
}