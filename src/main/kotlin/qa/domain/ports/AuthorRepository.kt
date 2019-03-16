package qa.domain.ports

import aggregates.Contributor
import qa.domain.valueObjects.ContributorIdentity

interface AuthorRepository {
    fun getAuthorById(authorIdentity : ContributorIdentity) : Contributor
}