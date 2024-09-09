package com.alexmprog.thecocktails.feature.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.alexmprog.thecocktails.categories.list.CategoriesListScreenRoute
import com.alexmprog.thecocktails.categories.list.categoriesScreenRoute
import com.alexmprog.thecocktails.core.model.Category
import com.alexmprog.thecocktails.core.model.Glass
import com.alexmprog.thecocktails.core.model.Ingredient
import com.alexmprog.thecocktails.core.ui.navigation.ScreenRoute
import com.alexmprog.thecocktails.feature.glasses.list.GlassesListScreenRoute
import com.alexmprog.thecocktails.feature.glasses.list.glassesScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.list.IngredientsListScreenRoute
import com.alexmprog.thecocktails.feature.ingredients.list.ingredientsScreenRoute
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    useBottomBar: Boolean,
    onCategoryClick: (Category) -> Unit,
    onIngredientClick: (Ingredient) -> Unit,
    onGlassClick: (Glass) -> Unit
) {
    val navBarItems = remember {
        listOf(
            NavItem(
                titleRes = R.string.feature_home_categories,
                screenRoute = CategoriesListScreenRoute,
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            ), NavItem(
                titleRes = R.string.feature_home_ingredients,
                screenRoute = IngredientsListScreenRoute,
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info
            ), NavItem(
                titleRes = R.string.feature_home_glasses,
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
    if (useBottomBar) {
        HomeBottomBar(
            navBarItems,
            findItem,
            navController,
            onCategoryClick,
            onIngredientClick,
            onGlassClick
        )
    } else {
        HomeModalDrawer(
            navBarItems,
            findItem,
            navController,
            onCategoryClick,
            onIngredientClick,
            onGlassClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBottomBar(
    navItems: List<NavItem>,
    currentItem: NavItem?,
    navHostController: NavHostController,
    onCategoryClick: (Category) -> Unit,
    onIngredientClick: (Ingredient) -> Unit,
    onGlassClick: (Glass) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            currentItem?.let {
                TopAppBar(title = { Text(stringResource(it.titleRes)) })
            }
        },
        bottomBar = {
            HomeNavigationBar(navItems, navHostController)
        }
    )
    { innerPadding ->
        HomeHavHost(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navHostController,
            onCategoryClick,
            onIngredientClick,
            onGlassClick
        )
    }
}

@Composable
private fun HomeNavigationBar(
    navItems: List<NavItem>,
    navHostController: NavHostController
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navHostController.navigate(item.screenRoute) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeModalDrawer(
    navItems: List<NavItem>,
    currentItem: NavItem?,
    navHostController: NavHostController,
    onCategoryClick: (Category) -> Unit,
    onIngredientClick: (Ingredient) -> Unit,
    onGlassClick: (Glass) -> Unit
) {
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
                                navHostController.navigate(item.screenRoute) {
                                    selectedTabIndex = index
                                    popUpTo(navHostController.graph.findStartDestination().id) {
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
                currentItem?.let {
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
                        })
                }
            }
        )
        { innerPadding ->
            HomeHavHost(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navHostController,
                onCategoryClick,
                onIngredientClick,
                onGlassClick
            )
        }
    }
}

@Composable
private fun HomeHavHost(
    modifier: Modifier, navController: NavHostController,
    onCategoryClick: (Category) -> Unit,
    onIngredientClick: (Ingredient) -> Unit,
    onGlassClick: (Glass) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CategoriesListScreenRoute
    ) {
        categoriesScreenRoute { onCategoryClick(it) }
        ingredientsScreenRoute { onIngredientClick(it) }
        glassesScreenRoute { onGlassClick(it) }
    }
}

private data class NavItem(
    @StringRes
    val titleRes: Int,
    val screenRoute: ScreenRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)