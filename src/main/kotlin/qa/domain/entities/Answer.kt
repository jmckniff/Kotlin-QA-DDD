package qa.domain.entities

import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.ContributorIdentity

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

}
