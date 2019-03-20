package qa.persistence.inMemory

import org.springframework.stereotype.Repository
import qa.domain.aggregates.Question
import qa.domain.ports.QuestionRepository
import qa.domain.valueObjects.*
import java.util.*

@Repository
class InMemoryQuestionRepository : QuestionRepository {

    private val _author = Contributor(ContributorIdentity(UUID.randomUUID()), Name("Jamie", "Mckniff"), Reputation(1000))

    private var _questions : MutableList<Question> = mutableListOf<Question>(
            Question(QuestionIdentity(UUID.randomUUID()), _author,"Some QuestionDto Title 1", "Some description", mutableListOf()),
            Question(QuestionIdentity(UUID.randomUUID()), _author, "Some QuestionDto Title 2", "Some description", mutableListOf())
    )

    override fun getQuestionsByAuthorId(authorIdentity: ContributorIdentity): List<Question> {
        return _questions
    }

    override fun createQuestion(question: Question) {
        _questions.add(question)
    }

    override fun getQuestionById(questionIdentity: QuestionIdentity): Question {
        return _questions.first { it.identity == questionIdentity }
    }

}