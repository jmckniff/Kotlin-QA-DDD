package qa.persistence.inMemory

import ValueObjects.Name
import ValueObjects.Reputation
import aggregates.Contributor
import org.springframework.stereotype.Repository
import qa.domain.ports.AuthorRepository
import qa.domain.valueObjects.ContributorIdentity
import java.util.*

@Repository
class InMemoryAuthorRepository : AuthorRepository {

    override fun getAuthorById(authorIdentity: ContributorIdentity): Contributor {
        return Contributor(
                ContributorIdentity(UUID.randomUUID()),
                Name("Jamie", "Mckniff"),
                Reputation(1000))
    }
}