package qa.services

import org.springframework.stereotype.Service
import qa.domain.ports.AuthorRepository
import qa.domain.ports.QuestionRepository
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity
import qa.services.dto.Question
import java.util.*

@Service
class QuestionService (val questionRepository: QuestionRepository, val authorRepository: AuthorRepository) {

    fun getQuestions(authorId : UUID) : List<Question> {

        var authorIdentity = ContributorIdentity(UUID.randomUUID())

        var questions = questionRepository.getQuestionsByAuthorId(authorIdentity)

        return questions.map { Question(it.identity.id, it.author.identity.id, it.title, it.description) }
    }

    fun askQuestion(question: Question) {

        val author = authorRepository.getAuthorById(ContributorIdentity(question.authorId))

        val questionAggregate = aggregates.Question(QuestionIdentity(question.id), author, question.title, question.description,  mutableListOf())

        questionRepository.createQuestion(questionAggregate)
    }
}