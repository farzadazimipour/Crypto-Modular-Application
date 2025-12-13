package com.example.domain.usecase

import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Rule

class GetCoinsUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var coinRepository: CoinRepository

    private lateinit var underTest: GetCoinsUseCase

    @Before
    fun setUp() {
        underTest = GetCoinsUseCase(coinRepository)
    }

    @Test
    fun `invoke should return list of coins from repository`() = runTest {
        // Given
        val expectedCoins = listOf(
            Coin(
                id = "bitcoin",
                isActive = true,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC"
            ),
            Coin(
                id = "ethereum",
                isActive = true,
                name = "Ethereum",
                rank = 2,
                symbol = "ETH"
            )
        )
        coEvery { coinRepository.getCoins() } returns expectedCoins

        // When
        val result = underTest()

        // Then
        assertEquals(expectedCoins, result)
        coVerify(exactly = 1) { coinRepository.getCoins() }
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        coEvery { coinRepository.getCoins() } returns emptyList()

        // When
        val result = underTest()

        // Then
        assertEquals(emptyList<Coin>(), result)
        coVerify(exactly = 1) { coinRepository.getCoins() }
    }
}