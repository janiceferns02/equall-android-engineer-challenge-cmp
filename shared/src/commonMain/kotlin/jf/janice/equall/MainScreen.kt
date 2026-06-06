package jf.janice.equall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jf.janice.equall.navigation.BottomNavDestination
import jf.janice.equall.navigation.MainNavGraph
import jf.janice.equall.ui.components.EquallBottomNavigationBar
import jf.janice.equall.ui.components.EquallTopBar
import jf.janice.equall.ui.theme.EquallBackground

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = BottomNavDestination.fromRoute(navBackStackEntry?.destination?.route)
        ?: BottomNavDestination.Home

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = EquallBackground,
        topBar = {
            EquallTopBar()
        },
        bottomBar = {
            EquallBottomNavigationBar(
                currentDestination = currentDestination,
                onDestinationSelected = { destination ->
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        MainNavGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
