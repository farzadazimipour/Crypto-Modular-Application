package com.example.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class CoinDetailTest {

    @Test
    fun `coinDetail should be created with correct properties`() {
        // Given
        val coinId = "bitcoin"
        val name = "Bitcoin"
        val description = "Bitcoin is a cryptocurrency"
        val symbol = "BTC"
        val rank = 1
        val isActive = true
        val tags = listOf("cryptocurrency", "digital-currency")
        val team = listOf(
            TeamMember("1", "Satoshi Nakamoto", "Founder")
        )

        // When
        val coinDetail = CoinDetail(
            coinId = coinId,
            name = name,
            description = description,
            symbol = symbol,
            rank = rank,
            isActive = isActive,
            tags = tags,
            team = team
        )

        // Then
        assertEquals(coinId, coinDetail.coinId)
        assertEquals(name, coinDetail.name)
        assertEquals(description, coinDetail.description)
        assertEquals(symbol, coinDetail.symbol)
        assertEquals(rank, coinDetail.rank)
        assertEquals(isActive, coinDetail.isActive)
        assertEquals(tags, coinDetail.tags)
        assertEquals(team, coinDetail.team)
    }

    @Test
    fun `coinDetail should handle empty tags and team`() {
        // Given
        val coinDetail = CoinDetail(
            coinId = "test-coin",
            name = "Test Coin",
            description = "A test cryptocurrency",
            symbol = "TEST",
            rank = 999,
            isActive = true,
            tags = emptyList(),
            team = emptyList()
        )

        // When & Then
        assertTrue(coinDetail.tags.isEmpty())
        assertTrue(coinDetail.team.isEmpty())
    }

    @Test
    fun `coinDetail should handle multiple team members`() {
        // Given
        val team = listOf(
            TeamMember("1", "Alice", "Developer"),
            TeamMember("2", "Bob", "Designer"),
            TeamMember("3", "Charlie", "Manager")
        )
        val coinDetail = CoinDetail(
            coinId = "multi-team-coin",
            name = "Multi Team Coin",
            description = "A coin with multiple team members",
            symbol = "MTC",
            rank = 50,
            isActive = true,
            tags = listOf("team", "collaboration"),
            team = team
        )

        // When & Then
        assertEquals(3, coinDetail.team.size)
        assertEquals("Alice", coinDetail.team[0].name)
        assertEquals("Developer", coinDetail.team[0].position)
    }
}