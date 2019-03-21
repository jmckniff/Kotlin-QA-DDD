package qa.domain.entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import qa.domain.exceptions.InsufficientReputationException
import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.*
import java.util.*

internal class AnswerTests {

    @Test
    fun accept_Should_Mark_Answer_AsAccepted() {
        var answerAuthor = getDefaultContributor()
        val questionAuthorIdentity = answerAuthor.identity
        val answer = getDefaultAnswer(answerAuthor)

        answer.accept(questionAuthorIdentity)

        assertEquals(true, answer.isAccepted, "The answer should be marked as accepted")
    }

    @Test
    fun accept_Should_Throw_If_Acceptor_IsNot_QuestionAuthor() {
        val author = getDefaultContributor()
        val randomContributor = ContributorIdentity(UUID.randomUUID())
        val answer = getDefaultAnswer(author)

        assertThrows<InvalidContributorException> {
            answer.accept(randomContributor)
        }
    }

    @Test
    fun unaccept_Should_Mark_Answer_AsNotAccepted() {
        var answerAuthor = getDefaultContributor()
        val questionAuthorIdentity = answerAuthor.identity
        val answer = getDefaultAnswer(answerAuthor, true)

        answer.unaccept(questionAuthorIdentity)

        assertEquals(false, answer.isAccepted, "The answer should be marked as NOT accepted")
    }

    @Test
    fun unaccept_Should_Throw_If_Unacceptor_IsNot_QuestionAuthor() {
        val author = getDefaultContributor()
        val randomContributor = ContributorIdentity(UUID.randomUUID())
        val answer = getDefaultAnswer(author, true)

        assertThrows<InvalidContributorException> {
            answer.unaccept(randomContributor)
        }
    }

    @Test
    fun upvote_Should_Throw_If_Upvoter_Does_Not_Have_EnoughReputation() {
        val author = getDefaultContributor()
        val answer = getDefaultAnswer(author, true)

        val contributor = getDefaultContributor()

        assertThrows<InsufficientReputationException> {
            answer.upvote(contributor)
        }
    }

    @Test
    fun upvote_Should_Increment_Votes_By_One() {
        val author = getDefaultContributor()
        val answer = getDefaultAnswer(author, true)
        val contributor = getDefaultContributor(1000)

        answer.upvote(contributor)

        assertEquals(1, answer.votes, "The answer vote count should be 1")
    }

    @Test
    fun downvote_Should_Throw_If_Downvoter_Does_Not_Have_EnoughReputation() {
        val author = getDefaultContributor()
        val answer = getDefaultAnswer(author, true)

        val contributor = getDefaultContributor()

        assertThrows<InsufficientReputationException> {
            answer.downvote(contributor)
        }
    }

    @Test
    fun downvote_Should_DeIncrement_Votes_By_One() {
        val author = getDefaultContributor()
        val answer = getDefaultAnswer(author, true)
        val contributor = getDefaultContributor(1000)

        answer.downvote(contributor)

        assertEquals(-1, answer.votes, "The answer vote count should be -1")
    }

    fun getDefaultContributor(reputationScore : Int = 0) : Contributor {
        val author = Contributor(
                ContributorIdentity(UUID.randomUUID()),
                Name("Joe", "Bloggs"),
                Reputation(reputationScore))

        return author
    }

    fun getDefaultAnswer(author : Contributor, isAccepted : Boolean = false) : Answer {
        val answer = Answer(
                AnswerIdentity(UUID.randomUUID()),
                author.identity,
                author,
                "This is the description of the answer",
                isAccepted,
                0)

        return answer
    }
}