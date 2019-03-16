package aggregates

import Entities.Answer
import Events.AnswerAcceptedEvent
import qa.domain.events.AnswerUnacceptedEvent
import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

class Question(val identity : QuestionIdentity,
               val author : Contributor,
               val title : String,
               val description : String,
               answers : MutableList<Answer>) : AggregateRoot() {

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

        if (contributorIdentity === author.identity) {
            throw InvalidContributorException("Only the question author may accept the answer")
        }

        answer.accept()
        answers.add(answer)

        queueEvent(AnswerAcceptedEvent(answer))
    }

    fun unacceptAnswer(answer : Answer, contributorIdentity: ContributorIdentity) {
        if (contributorIdentity === author.identity) {
            throw InvalidContributorException("Only the question author may unaccept the answer")
        }

        answer.unaccept()

        queueEvent(AnswerUnacceptedEvent(answer))
    }

    fun getAuthorIdentity(): ContributorIdentity {
        return author.identity
    }
}