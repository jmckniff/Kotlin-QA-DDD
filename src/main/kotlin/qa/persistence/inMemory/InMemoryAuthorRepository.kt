package qa.persistence.inMemory

import org.springframework.stereotype.Repository
import qa.domain.ports.AuthorRepository
import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.Name
import qa.domain.valueObjects.Reputation
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