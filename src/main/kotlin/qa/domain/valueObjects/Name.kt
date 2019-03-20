package qa.domain.valueObjects

class Name(val forename : String, val surname : String) {
    fun getFullName() : String {
        return "$forename $surname"
    }
}