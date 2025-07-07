package com.example.cruditemapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cruditemapp.ui.ItemViewModel
import com.example.cruditemapp.ui.screens.ItemFormScreen
import com.example.cruditemapp.ui.screens.ItemListScreen

sealed class Screen(val route: String) {
    object ItemList : Screen("item_list")
    object ItemForm : Screen("item_form")
    object ItemEdit : Screen("item_edit/{itemId}") {
        fun createRoute(itemId: String) = "item_edit/$itemId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: ItemViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ItemList.route
    ) {
        composable(Screen.ItemList.route) {
            ItemListScreen(
                viewModel = viewModel,
                onNavigateToAdd = {
                    navController.navigate(Screen.ItemForm.route)
                },
                onNavigateToEdit = { item ->
                    navController.navigate(Screen.ItemEdit.createRoute(item.id))
                }
            )
        }

        composable(Screen.ItemForm.route) {
            ItemFormScreen(
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ItemEdit.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            val itemToEdit = viewModel.uiState.value.items.find { it.id == itemId }

            ItemFormScreen(
                viewModel = viewModel,
                itemToEdit = itemToEdit,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
