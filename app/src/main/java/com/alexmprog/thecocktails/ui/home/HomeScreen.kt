package com.alexmprog.thecocktails.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexmprog.thecocktails.R
import com.alexmprog.thecocktails.categories.CategoriesListScreenRoute
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.feature.glasses.GlassesListScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.IngredientsListScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostBuilder: @Composable (Modifier, NavHostController) -> Unit) {
    val navBarItems = remember {
        listOf(
            NavBarItem(
                titleRes = R.string.categories,
                screenRoute = CategoriesListScreenRoute,
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Outlined.Menu
            ), NavBarItem(
                titleRes = R.string.ingredients,
                screenRoute = IngredientsListScreenRoute,
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info
            ), NavBarItem(
                titleRes = R.string.glasses,
                screenRoute = GlassesListScreenRoute,
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart
            )
        )
    }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val findItem = navBarItems.find { it.screenRoute.javaClass.canonicalName == currentDestination }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            findItem?.let { TopAppBar(title = { Text(stringResource(it.titleRes)) }) }
        },
        bottomBar = {
            findItem?.let { HomeNavigationBar(navBarItems, navController) }
        }
    )
    { innerPadding ->
        navHostBuilder(
            Modifier
                .fillMaxSize()
                .padding(innerPadding), navController
        )
    }
}

@Composable
private fun HomeNavigationBar(nabBarItems: List<NavBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        nabBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(item.screenRoute){
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selectedTabIndex == index) item.selectedIcon
                        else item.unselectedIcon,
                        contentDescription = stringResource(item.titleRes)
                    )
                },
                label = { Text(stringResource(item.titleRes)) })
        }
    }
}

private data class NavBarItem(
    @StringRes
    val titleRes: Int,
    val screenRoute: ScreenRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)