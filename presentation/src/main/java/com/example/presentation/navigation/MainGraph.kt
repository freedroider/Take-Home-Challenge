package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.common.Config
import com.example.presentation.screens.DashboardScreen
import com.example.presentation.utils.decodeURL
import com.example.presentation.utils.encodeURL

@Composable
fun MainGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "dashboard?url={url},title={title}"
    ) {
        composable(
            route = "dashboard?url={url},title={title}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("title") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments
                ?.getString("title")
                ?.decodeURL
                ?: "."
            val url = backStackEntry.arguments
                ?.getString("url")
                ?.decodeURL
                ?: Config.endpoint
            DashboardScreen(
                viewModel = hiltViewModel(),
                title = title,
                url = url,
                backEnabled = navController.previousBackStackEntry != null,
                onBack = { navController.popBackStack() },
                navigateToRadioElement = { element ->
                    navController.navigate("dashboard?url=${element.url?.encodeURL},title=$title/${element.text.encodeURL}")
                }
            )
        }
    }
}