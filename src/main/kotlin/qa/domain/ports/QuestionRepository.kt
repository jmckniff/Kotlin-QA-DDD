package qa.domain.ports

import aggregates.Question
import qa.domain.valueObjects.ContributorIdentity

interface QuestionRepository {
    fun getQuestionsByAuthorId(authorIdentity : ContributorIdentity) : List<Question>
    fun createQuestion(question: Question)
}