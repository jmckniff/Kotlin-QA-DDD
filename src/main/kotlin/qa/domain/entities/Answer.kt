package Entities

import Events.AnswerAcceptedEvent
import aggregates.Contributor
import aggregates.Entity
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

class Answer(val questionId : QuestionIdentity, val author : Contributor, val description : String, isAccepted: Boolean) : Entity() {

    var isAccepted : Boolean = isAccepted
        private set

    fun accept() {
        isAccepted = true
    }

    fun unaccept() {
        isAccepted = false
    }


    fun getAuthorIdentity() : ContributorIdentity {
        return author.identity
    }
}
