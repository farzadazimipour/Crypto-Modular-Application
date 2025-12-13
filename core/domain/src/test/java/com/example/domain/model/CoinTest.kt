package com.example.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class CoinTest {

    @Test
    fun `coin should be created with correct properties`() {
        // Given
        val id = "bitcoin"
        val isActive = true
        val name = "Bitcoin"
        val rank = 1
        val symbol = "BTC"

        // When
        val coin = Coin(
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol
        )

        // Then
        assertEquals(id, coin.id)
        assertEquals(isActive, coin.isActive)
        assertEquals(name, coin.name)
        assertEquals(rank, coin.rank)
        assertEquals(symbol, coin.symbol)
    }

    @Test
    fun `coins with same properties should be equal`() {
        // Given
        val coin1 = Coin(
            id = "bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC"
        )
        val coin2 = Coin(
            id = "bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC"
        )

        // When & Then
        assertEquals(coin1, coin2)
        assertEquals(coin1.hashCode(), coin2.hashCode())
    }

    @Test
    fun `coins with different properties should not be equal`() {
        // Given
        val coin1 = Coin(
            id = "bitcoin",
            isActive = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC"
        )
        val coin2 = Coin(
            id = "ethereum",
            isActive = true,
            name = "Ethereum",
            rank = 2,
            symbol = "ETH"
        )

        // When & Then
        assertFalse(coin1 == coin2)
    }

    @Test
    fun `coin should handle inactive status`() {
        // Given
        val coin = Coin(
            id = "inactive-coin",
            isActive = false,
            name = "Inactive Coin",
            rank = 100,
            symbol = "INC"
        )

        // When & Then
        assertFalse(coin.isActive)
    }
}