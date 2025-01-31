package com.alexmprog.thecocktails.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.feature.home.R
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    homeScreens: HomeScreens,
    onSettingsClick: () -> Unit
) {
    val navBarItems = remember {
        listOf(
            NavItem(
                titleRes = R.string.categories,
                screenRoute = homeScreens.categoriesScreen.route,
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            ), NavItem(
                titleRes = R.string.ingredients,
                screenRoute = homeScreens.ingredientsScreen.route,
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info
            ), NavItem(
                titleRes = R.string.glasses,
                screenRoute = homeScreens.glassesScreen.route,
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart
            )
        )
    }
    val navController = rememberNavController()
    HomeModalDrawer(
        navBarItems,
        navController,
        homeScreens,
        onSettingsClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeModalDrawer(
    navItems: List<NavItem>,
    navController: NavHostController,
    homeScreens: HomeScreens,
    onSettingsClick: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val findItem = navItems.find { it.screenRoute.javaClass.canonicalName == currentDestination }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    navItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            selected = selectedTabIndex == index,
                            icon = {
                                Icon(
                                    imageVector = if (selectedTabIndex == index) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = stringResource(item.titleRes)
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(item.titleRes),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.W400
                                )
                            },
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
                                navController.navigate(item.screenRoute) {
                                    selectedTabIndex = index
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                findItem?.let {
                    TopAppBar(title = { Text(stringResource(it.titleRes)) },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { onSettingsClick() }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null
                                )
                            }
                        })
                }
            }
        )
        { innerPadding ->
            HomeHavHost(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController,
                homeScreens
            )
        }
    }
}

@Composable
private fun HomeHavHost(
    modifier: Modifier, navController: NavHostController,
    homeScreens: HomeScreens
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = homeScreens.categoriesScreen.route
    ) {
        homeScreens.categoriesScreen.builder(this)
        homeScreens.ingredientsScreen.builder(this)
        homeScreens.glassesScreen.builder(this)
    }
}

private data class NavItem(
    @StringRes
    val titleRes: Int,
    val screenRoute: ScreenRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)