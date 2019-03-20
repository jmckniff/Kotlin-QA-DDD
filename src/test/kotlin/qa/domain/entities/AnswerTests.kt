package qa.domain.entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.*
import java.util.*

internal class AnswerTests {

    @Test
    fun accept_Should_Mark_Answer_AsAccepted() {
        var answerAuthor = getDefaultAuthor()
        val questionAuthorIdentity = answerAuthor.identity
        val answer = getDefaultAnswer(answerAuthor)

        answer.accept(questionAuthorIdentity)

        assertEquals(true, answer.isAccepted, "The answer should be marked as accepted")
    }

    @Test
    fun accept_Should_Throw_If_Acceptor_IsNot_QuestionAuthor() {
        val author = getDefaultAuthor()
        val randomContributor = ContributorIdentity(UUID.randomUUID())
        val answer = getDefaultAnswer(author)

        assertThrows<InvalidContributorException> {
            answer.accept(randomContributor)
        }
    }

    @Test
    fun unaccept_Should_Mark_Answer_AsNotAccepted() {
        var answerAuthor = getDefaultAuthor()
        val questionAuthorIdentity = answerAuthor.identity
        val answer = getDefaultAnswer(answerAuthor, true)

        answer.unaccept(questionAuthorIdentity)

        assertEquals(false, answer.isAccepted, "The answer should be marked as NOT accepted")
    }

    @Test
    fun unaccept_Should_Throw_If_Unacceptor_IsNot_QuestionAuthor() {
        val author = getDefaultAuthor()
        val randomContributor = ContributorIdentity(UUID.randomUUID())
        val answer = getDefaultAnswer(author, true)

        assertThrows<InvalidContributorException> {
            answer.unaccept(randomContributor)
        }
    }

    fun getDefaultAuthor() : Contributor {
        val author = Contributor(
                ContributorIdentity(UUID.randomUUID()),
                Name("Joe", "Bloggs"),
                Reputation(0))

        return author
    }

    fun getDefaultAnswer(author : Contributor, isAccepted : Boolean = false) : Answer {
        val answer = Answer(
                AnswerIdentity(UUID.randomUUID()),
                author.identity,
                author,
                "This is the description of the answer",
                isAccepted)

        return answer
    }
}