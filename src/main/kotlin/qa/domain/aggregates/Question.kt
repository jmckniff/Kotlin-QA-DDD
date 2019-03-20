package qa.domain.aggregates

import qa.domain.entities.Answer
import qa.domain.events.AnswerAcceptedEvent
import qa.domain.events.AnswerUnacceptedEvent
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

class Question(val identity : QuestionIdentity,
               val author : Contributor,
               val title : String,
               val description : String,
               answers : MutableList<Answer> = mutableListOf()) : AggregateRoot() {

    var answers: MutableList<Answer> = answers
        private set

    var acceptedAnswer : Answer? = null
        get() = answers.firstOrNull { it.isAccepted }
        private set

    val hasAcceptedAnswer : Boolean get() = answers.any { it.isAccepted }

    fun addAnswer(answer : Answer) {
        answers.add(answer)
    }

    fun acceptAnswer(answer : Answer, contributorIdentity : ContributorIdentity) {
        answer.accept(contributorIdentity)
        queueEvent(AnswerAcceptedEvent(answer))
    }

    fun unacceptAnswer(answer : Answer, contributorIdentity: ContributorIdentity) {
        answer.unaccept(contributorIdentity)
        queueEvent(AnswerUnacceptedEvent(answer))
    }

    fun getAuthorIdentity(): ContributorIdentity {
        return author.identity
    }

    fun getAnswer(answerIdentity: AnswerIdentity): Answer {
        return answers.first { it.identity == answerIdentity}
    }
}