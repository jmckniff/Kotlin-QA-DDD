package qa.domain.aggregates

import qa.domain.entities.Answer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import qa.domain.events.AnswerUnacceptedEvent
import qa.domain.valueObjects.*
import java.util.*

internal class QuestionTests {

    @Test
    fun acceptAnswer_Should_Queue_AnswerAccepted_Event() {
        val author = getDefaultAuthor()
        val question = getDefaultQuestion(author)
        val answer = getDefaultAnswer(question.author)
        question.addAnswer(answer)

        question.acceptAnswer(answer, author.identity)

        assertEquals(true, question.hasAcceptedAnswer, "The question should have an accepted answer")
        assertEquals(question.acceptedAnswer, answer, "The accepted answer should be the same as the answer")
        assertEquals(1, question.queuedEvents.count(), "An answer accepted event should have been raised")
    }

    @Test
    fun unacceptAnswer_Should_Queue_AnswerUnacceptedEvent() {
        val author = getDefaultAuthor()
        val question = getDefaultQuestion(author)
        val answer = getDefaultAnswer(question.author)
        question.acceptAnswer(answer, question.getAuthorIdentity())

        question.unacceptAnswer(answer, question.getAuthorIdentity())

        assertEquals(false, question.hasAcceptedAnswer, "The question should NOT have an accepted answer")
        assertEquals(null, question.acceptedAnswer, "The accepted answer should be the same as the answer")
        assertEquals(true, question.queuedEvents.any { it is AnswerUnacceptedEvent }, "An answer unaccepted event should have been raised")
    }

    @Test
    fun addAnswer_Should_Add_An_Answer() {
        val author = getDefaultAuthor()
        val question = getDefaultQuestion(author)
        val answer = getDefaultAnswer(question.author)

        question.addAnswer(answer)

        assertEquals(1, question.answers.count(), "The question should have one answer")
    }

    private fun getDefaultAuthor() : Contributor {
        val name = Name("Jamie", "Mckniff")
        val contributorIdentity = ContributorIdentity(UUID.randomUUID())
        val author = Contributor(contributorIdentity, name, Reputation(100))

        return author
    }

    private fun getDefaultQuestion(author : Contributor) : Question {
        val questionIdentity = QuestionIdentity(UUID.randomUUID())
        val question = Question(questionIdentity, author, "What does the fox say?", "I need to know what the fox says.",  mutableListOf())

        return question
    }

    private fun getDefaultAnswer(author: Contributor): Answer {
        val answer = Answer(
                AnswerIdentity(UUID.randomUUID()),
                author.identity,
                author,
                "The fox says WAPAPAPOW PA POW PA POW!",
                false,
                0)

        return answer
    }
}