package tests.Aggregates

import aggregates.Question
import Entities.Answer
import ValueObjects.Name
import ValueObjects.Reputation
import aggregates.Contributor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity
import java.util.*

internal class QuestionTests {

    @Test
    fun acceptAnswer_Should_Queue_AnswerAccepted_Event() {
        val question = getDefaultQuestion()
        val answer = getDefaultAnswer(question.author)

        question.acceptAnswer(answer, question.getAuthorIdentity())

        assertEquals(true, question.hasAcceptedAnswer, "The question should have an accepted answer")
        assertEquals(question.acceptedAnswer, answer, "The accepted answer should be the same as the answer")
        assertEquals(1, question.queuedEvents.count(), "An answer accepted event should have been raised")
    }

    @Test
    fun addAnswer_Should_Add_An_Answer() {
        var question = getDefaultQuestion()
        var answer = getDefaultAnswer(question.author)

        question.addAnswer(answer)

        assertEquals(1, question.answers.count(), "The question should have one answer")
    }

    private fun getDefaultQuestion() : Question {
        val name = Name("Jamie", "Mckniff")
        val contributorIdentity = ContributorIdentity(UUID.randomUUID())
        val author = Contributor(contributorIdentity, name, Reputation(100))
        val questionIdentity = QuestionIdentity(UUID.randomUUID())

        val question = Question(questionIdentity, author, "What does the fox say?", "I need to know what the fox says.",  mutableListOf())

        return question
    }

    private fun getDefaultAnswer(author: Contributor): Answer {
        val questionIdentity = QuestionIdentity(UUID.randomUUID())
        val contributor = Contributor(author.identity, Name("Anna", "Lewicki"), Reputation(0))

        val answer = Answer(questionIdentity, contributor, "The fox says WAPAPAPOW PA POW PA POW!", isAccepted = false)

        return answer

    }
}