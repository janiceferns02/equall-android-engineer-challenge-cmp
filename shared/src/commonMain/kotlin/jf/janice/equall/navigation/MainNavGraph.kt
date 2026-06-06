package jf.janice.equall.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jf.janice.equall.domain.HomeUiState
import jf.janice.equall.ui.screens.HomeScreen
import jf.janice.equall.ui.screens.OtherScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Home.route,
        modifier = modifier,
    ) {
        composable(BottomNavDestination.Home.route) {
            HomeScreen()
        }
        composable(BottomNavDestination.Loans.route) {
            OtherScreen("Loans")
        }
        composable(BottomNavDestination.Explore.route) {
            OtherScreen("Explore")
        }
        composable(BottomNavDestination.Profile.route) {
            OtherScreen("Profile")
        }
    }
}
