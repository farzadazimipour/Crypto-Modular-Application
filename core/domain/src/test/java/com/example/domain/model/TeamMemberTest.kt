package com.example.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class TeamMemberTest {

    @Test
    fun `teamMember should be created with correct properties`() {
        // Given
        val id = "1"
        val name = "Satoshi Nakamoto"
        val position = "Founder"

        // When
        val teamMember = TeamMember(
            id = id,
            name = name,
            position = position
        )

        // Then
        assertEquals(id, teamMember.id)
        assertEquals(name, teamMember.name)
        assertEquals(position, teamMember.position)
    }

    @Test
    fun `teamMembers with same properties should be equal`() {
        // Given
        val teamMember1 = TeamMember("1", "Alice", "Developer")
        val teamMember2 = TeamMember("1", "Alice", "Developer")

        // When & Then
        assertEquals(teamMember1, teamMember2)
        assertEquals(teamMember1.hashCode(), teamMember2.hashCode())
    }

    @Test
    fun `teamMembers with different properties should not be equal`() {
        // Given
        val teamMember1 = TeamMember("1", "Alice", "Developer")
        val teamMember2 = TeamMember("2", "Bob", "Designer")

        // When & Then
        assertNotEquals(teamMember1, teamMember2)
    }

    @Test
    fun `teamMembers with same id but different details should not be equal`() {
        // Given
        val teamMember1 = TeamMember("1", "Alice", "Developer")
        val teamMember2 = TeamMember("1", "Alice", "Senior Developer")

        // When & Then
        assertNotEquals(teamMember1, teamMember2)
    }
}