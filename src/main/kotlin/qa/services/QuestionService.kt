package qa.services

import aggregates.Question
import org.springframework.stereotype.Service
import qa.domain.entities.Answer
import qa.domain.ports.AuthorRepository
import qa.domain.ports.QuestionRepository
import qa.domain.valueObjects.AnswerIdentity
import qa.domain.valueObjects.ContributorIdentity
import qa.domain.valueObjects.QuestionIdentity
import qa.services.dto.AnswerDto
import qa.services.dto.QuestionDto
import java.util.*

@Service
class QuestionService (val questionRepository: QuestionRepository, val authorRepository: AuthorRepository) {

    fun getQuestions(authorId : UUID) : List<QuestionDto> {
        var authorIdentity = ContributorIdentity(UUID.randomUUID())
        var questions = questionRepository.getQuestionsByAuthorId(authorIdentity)

        return questions.map { QuestionDto(it.identity.id, it.author.identity.id, it.title, it.description) }
    }

    fun askQuestion(questionDto: QuestionDto) {
        val author = authorRepository.getAuthorById(ContributorIdentity(questionDto.authorId))

        val question = Question(
                QuestionIdentity(questionDto.id),
                author, questionDto.title,
                questionDto.description)

        questionRepository.createQuestion(question)
    }

    fun answerQuestion(answerDto : AnswerDto) {
        val author = authorRepository.getAuthorById(ContributorIdentity(answerDto.authorId))
        val question = questionRepository.getQuestionById(QuestionIdentity(answerDto.questionId))

        val answer = Answer(
            AnswerIdentity(answerDto.id),
            question.identity,
            author,
            answerDto.description)

        question.addAnswer(answer)
    }

    fun acceptAnswer(questionId : UUID, answerId : UUID, authorId : UUID) {
        val author = authorRepository.getAuthorById(ContributorIdentity(authorId))
        val question = questionRepository.getQuestionById(QuestionIdentity(questionId))

        val answer = question.getAnswer(AnswerIdentity(answerId))

        question.acceptAnswer(answer, author.identity)
    }

    fun unacceptAnswer(questionId : UUID, answerId : UUID, authorId : UUID) {
        val author = authorRepository.getAuthorById(ContributorIdentity(authorId))
        val question = questionRepository.getQuestionById(QuestionIdentity(questionId))

        val answer = question.getAnswer(AnswerIdentity(answerId))

        question.unacceptAnswer(answer, author.identity)
    }
}