package com.example.compose_crypto.list

import androidx.compose.runtime.Stable
import com.example.domain.model.Coin

@Stable
data class CryptoListScreenUiState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val errorState: ErrorState? = null,
)

sealed interface ErrorState {
    data object AirPlainMode : ErrorState
    data object NotConnected : ErrorState
    data class Other(val message: String? = null) : ErrorState
}