package qa.domain.entities

import qa.domain.events.AnswerUpvotedEvent
import qa.domain.exceptions.InsufficientReputationException
import qa.domain.exceptions.InvalidContributorException
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.ContributorIdentity

class Answer(val identity : AnswerIdentity,
             private val questionAuthorIdentity : ContributorIdentity,
             val author : Contributor,
             val description : String,
             isAccepted: Boolean = false,
             votes: Int) : Entity() {

    var isAccepted : Boolean = isAccepted
        private set

    var votes : Int = votes
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

    fun upvote(contributor: Contributor) {
        if (contributor.reputation.score < 100) {
            throw InsufficientReputationException("The contributor ${contributor.identity.id} has ${contributor.reputation.score} but requires 100 in order to upvote the answer")
        }

        votes++

        queueEvent(AnswerUpvotedEvent(this, contributor))
    }

    fun downvote(contributor: Contributor) {
        if (contributor.reputation.score < 100) {
            throw InsufficientReputationException("The contributor ${contributor.identity.id} has ${contributor.reputation.score} but requires 100 in order to downvote the answer")
        }

        votes--

        queueEvent(AnswerUpvotedEvent(this, contributor))
    }

}
