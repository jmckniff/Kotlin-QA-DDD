package qa.domain.ports

import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.ContributorIdentity

interface AuthorRepository {
    fun getAuthorById(authorIdentity : ContributorIdentity) : Contributor
}