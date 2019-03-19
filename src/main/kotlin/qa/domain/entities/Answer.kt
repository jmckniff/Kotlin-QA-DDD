package qa.domain.entities

import aggregates.Contributor
import aggregates.Entity
import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

class Answer(val identity : AnswerIdentity,
             private val questionAuthorIdentity : ContributorIdentity,
             val author : Contributor,
             val description : String,
             isAccepted: Boolean = false) : Entity() {

    var isAccepted : Boolean = isAccepted
        private set

    fun accept(contributorIdentity: ContributorIdentity) {
        if (contributorIdentity != questionAuthorIdentity) {
            throw InvalidContributorException("Only the question author may accept the answer")
        }

        isAccepted = true
    }

    fun unaccept(contributorIdentity: ContributorIdentity) {
        if (contributorIdentity != questionAuthorIdentity) {
            throw InvalidContributorException("Only the question author may unaccept the answer")
        }

        isAccepted = false
    }


    fun getAuthorIdentity() : ContributorIdentity {
        return author.identity
    }
}
