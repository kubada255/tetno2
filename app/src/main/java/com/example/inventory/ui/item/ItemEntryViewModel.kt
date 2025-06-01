
package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat


class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set


    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }


    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            wiek.isNotBlank() && wzrost.isNotBlank() && tetno.isNotBlank() &&  (cistolic.isBlank() || cistolic.toIntOrNull() != null) &&
                    (diastolic.isBlank() || diastolic.toIntOrNull() != null)
        }
    }
}


data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val wzrost: String = "",
    val wiek: String = "",
    val tetno: String = "",
    val cistolic: String = "",
    val diastolic: String = "",
    val waga: String = "",
    val data: String = ""
)


fun ItemDetails.toItem(): Item = Item(
    id = id,
    wiek = wiek.toIntOrNull() ?: 0,
    wzrost = wzrost.toIntOrNull() ?: 0,
    tetno = tetno.toIntOrNull() ?: 0,
    cistolic = cistolic.toIntOrNull() ?: 0,
    diastolic = diastolic.toIntOrNull() ?: 0,
    waga = waga.toIntOrNull() ?: 0,
    data =System.currentTimeMillis()
)




fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)


fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    wiek= wiek.toString(),
    wzrost=wzrost.toString(),
    tetno = tetno.toString(),
    cistolic = cistolic.toString(),
    diastolic = diastolic.toString()
)
