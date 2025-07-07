package com.example.cruditemapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cruditemapp.data.Item
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class ItemUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ItemViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val itemsRef = database.getReference("items")

    private val _uiState = MutableStateFlow(ItemUiState())
    val uiState: StateFlow<ItemUiState> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        
        itemsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Item>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Item::class.java)
                    item?.let { 
                        items.add(it.copy(id = childSnapshot.key ?: ""))
                    }
                }
                _uiState.value = _uiState.value.copy(
                    items = items.sortedByDescending { it.createdAt },
                    isLoading = false
                )
            }

            override fun onCancelled(error: DatabaseError) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Erro ao carregar itens: ${error.message}"
                )
            }
        })
    }

    fun addItem(name: String, description: String) {
        if (name.isBlank()) return

        viewModelScope.launch {
            try {
                val itemId = UUID.randomUUID().toString()
                val item = Item(
                    id = itemId,
                    name = name.trim(),
                    description = description.trim()
                )
                itemsRef.child(itemId).setValue(item)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Erro ao adicionar item: ${e.message}"
                )
            }
        }
    }

    fun updateItem(item: Item, name: String, description: String) {
        if (name.isBlank()) return

        viewModelScope.launch {
            try {
                val updatedItem = item.copy(
                    name = name.trim(),
                    description = description.trim()
                )
                itemsRef.child(item.id).setValue(updatedItem)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Erro ao atualizar item: ${e.message}"
                )
            }
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            try {
                itemsRef.child(item.id).removeValue()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Erro ao excluir item: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 