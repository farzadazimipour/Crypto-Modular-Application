package com.example.data.repository

import com.example.domain.model.Coin
import com.example.domain.model.CoinDetail
import com.example.domain.model.TeamMember
import com.example.network.dto.CoinDetailDto
import com.example.network.dto.CoinDto
import com.example.network.dto.LinksDto
import com.example.network.dto.TagDto
import com.example.network.dto.TeamMemberDto
import com.example.network.dto.WhitepaperDto
import com.example.network.retrofit.CryptoApiService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var apiService: CryptoApiService

    private lateinit var repository: CoinRepositoryImpl

    @Before
    fun setup() {
        repository = CoinRepositoryImpl(apiService)
    }

    @Test
    fun `getCoins returns mapped domain models`() = runTest {
        val coinDtos = listOf(
            CoinDto(
                id = "btc",
                isActive = true,
                isNew = false,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC",
                type = "coin"
            )
        )
        coEvery { apiService.getCoins() } returns coinDtos

        val result = repository.getCoins()

        assertEquals(1, result.size)
        assertEquals(
            Coin(
                id = "btc",
                isActive = true,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC"
            ),
            result[0]
        )
    }

    @Test
    fun `getCoin returns mapped domain model`() = runTest {
        val coinDetailDto = CoinDetailDto(
            id = "btc",
            name = "Bitcoin",
            description = "Bitcoin description",
            symbol = "BTC",
            rank = 1,
            isActive = true,
            tags = listOf(TagDto(1, 100, "tagId", "tag1")),
            team = listOf(TeamMemberDto("1", "Satoshi", "Creator")),
            developmentStatus = "Working",
            firstDataAt = "2009-01-03T00:00:00Z",
            hardwareWallet = true,
            hashAlgorithm = "SHA256",
            isNew = false,
            lastDataAt = "2023-01-01T00:00:00Z",
            links = LinksDto(
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
            ),
            linksExtended = emptyList(),
            message = "",
            openSource = true,
            orgStructure = "Decentralized",
            proofType = "Proof of Work",
            startedAt = "2009-01-03T00:00:00Z",
            type = "coin",
            whitepaper = WhitepaperDto("", "")
        )
        coEvery { apiService.getCoinById("btc") } returns coinDetailDto

        val result = repository.getCoin("btc")

        assertEquals(
            CoinDetail(
                coinId = "btc",
                name = "Bitcoin",
                description = "Bitcoin description",
                symbol = "BTC",
                rank = 1,
                isActive = true,
                tags = listOf("tag1"),
                team = listOf(TeamMember("1", "Satoshi", "Creator"))
            ),
            result
        )
    }
}