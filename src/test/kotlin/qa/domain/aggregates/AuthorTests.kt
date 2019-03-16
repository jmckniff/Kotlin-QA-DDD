package tests.Aggregates

import ValueObjects.Name
import ValueObjects.Reputation
import aggregates.Contributor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import qa.domain.valueObjects.ContributorIdentity
import java.util.*


internal class AuthorTests {

    @Test
    fun getFullName_Should_Return_FullName() {
        var author = getDefaultAuthor()

        assertEquals("Jamie Mckniff", author.getFullName(), "The authors full name should be 'Jamie Mckniff'")
    }

    @Test
    fun getReputationScore_Should_Return_Score() {
        var author = getDefaultAuthor(100)

        var reputationSore = author.getReputationScore()

        assertEquals(100, reputationSore, "The authors reputation score should be 100")
    }


    fun getDefaultAuthor(reputationScore : Int = 0) : Contributor {
        val name = Name("Jamie", "Mckniff")
        val identity = ContributorIdentity(UUID.randomUUID())
        var author = Contributor(identity, name, Reputation(reputationScore))

        return author
    }
}