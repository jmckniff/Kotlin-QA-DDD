package qa.domain.valueObjects

class Contributor (val identity: ContributorIdentity, val name: Name, val reputation: Reputation) {

    fun getFullName() : String {
        return name.getFullName()
    }

    fun getReputationScore(): Int {
        return reputation.score
    }
}