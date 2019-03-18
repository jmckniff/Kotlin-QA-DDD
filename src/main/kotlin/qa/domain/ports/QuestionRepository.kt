package qa.domain.ports

import aggregates.Question
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity

interface QuestionRepository {
    fun getQuestionsByAuthorId(authorIdentity : ContributorIdentity) : List<Question>
    fun createQuestion(question: Question)
    fun getQuestionById(questionIdentity: QuestionIdentity): Question
}