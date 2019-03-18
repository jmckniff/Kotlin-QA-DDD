package qa.domain.entities

import aggregates.Contributor
import aggregates.Entity
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

class Answer(val identity : AnswerIdentity, val questionId : QuestionIdentity, val author : Contributor, val description : String, isAccepted: Boolean = false) : Entity() {

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
