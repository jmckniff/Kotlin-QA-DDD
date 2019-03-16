package qa.persistence.inMemory

import ValueObjects.Name
import ValueObjects.Reputation
import aggregates.Contributor
import aggregates.Question
import org.springframework.stereotype.Repository
import qa.domain.ports.QuestionRepository
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity
import java.util.*

@Repository
class InMemoryQuestionRepository : QuestionRepository {

   private val _author = Contributor(ContributorIdentity(UUID.randomUUID()), Name("Jamie", "Mckniff"), Reputation(1000))

    private var _questions : MutableList<Question> = mutableListOf<Question>(
            Question(QuestionIdentity(UUID.randomUUID()), _author,"Some Question Title 1", "Some description", mutableListOf()),
            Question(QuestionIdentity(UUID.randomUUID()), _author, "Some Question Title 2", "Some description", mutableListOf())
    )

    override fun getQuestionsByAuthorId(authorIdentity: ContributorIdentity): List<Question> {
        return _questions
    }

    override fun createQuestion(question: Question) {
        _questions.add(question)
    }

}