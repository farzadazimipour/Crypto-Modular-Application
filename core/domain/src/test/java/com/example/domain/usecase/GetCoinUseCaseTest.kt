package com.example.domain.usecase

import com.example.domain.model.CoinDetail
import com.example.domain.model.TeamMember
import com.example.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCoinUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var coinRepository: CoinRepository

    private lateinit var underTest: GetCoinUseCase

    @Before
    fun setUp() {
        underTest = GetCoinUseCase(coinRepository)
    }

    @Test
    fun `invoke should return coin detail from repository`() = runTest {
        // Given
        val coinId = "bitcoin"
        val expectedCoinDetail = CoinDetail(
            coinId = coinId,
            name = "Bitcoin",
            description = "Bitcoin is a cryptocurrency",
            symbol = "BTC",
            rank = 1,
            isActive = true,
            tags = listOf("cryptocurrency", "digital-currency"),
            team = listOf(
                TeamMember(
                    id = "satoshi-nakamoto",
                    name = "Satoshi Nakamoto",
                    position = "Founder"
                )
            )
        )
        coEvery { coinRepository.getCoin(coinId) } returns expectedCoinDetail

        // When
        val result = underTest(coinId)

        // Then
        assertEquals(expectedCoinDetail, result)
        coVerify(exactly = 1) { coinRepository.getCoin(coinId) }
    }
}