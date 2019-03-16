package aggregates

import ValueObjects.Name
import ValueObjects.Reputation
import qa.domain.valueObjects.ContributorIdentity
import java.util.*

class Contributor (val identity: ContributorIdentity, val name: Name, val reputation: Reputation) {

    fun getFullName() : String {
        return name.getFullName()
    }

    fun getReputationScore(): Int {
        return reputation.score
    }

}