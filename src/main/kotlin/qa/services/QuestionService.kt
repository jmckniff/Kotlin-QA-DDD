package qa.services

import org.springframework.stereotype.Service
import qa.domain.aggregates.Question
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


    // Todo this should return a lightweight summary of the question
    fun getQuestions(authorId : UUID) : List<QuestionDto> {
        var authorIdentity = ContributorIdentity(UUID.randomUUID())
        var questions = questionRepository.getQuestionsByAuthorId(authorIdentity)

        return questions.map {
            QuestionDto(
                it.identity.id,
                it.author.identity.id,
                it.title,
                it.description,
                it.answers.map {
                    AnswerDto(it.identity.id, it.author.identity.id, it.description)
                })
        }
    }

    fun askQuestion(questionDto: QuestionDto) {
        val author = authorRepository.getAuthorById(ContributorIdentity(questionDto.authorId))

        val question = Question(
                QuestionIdentity(questionDto.id),
                author, questionDto.title,
                questionDto.description)

        questionRepository.createQuestion(question)
    }

    fun getQuestion(questionId: UUID): QuestionDto {
        val question = questionRepository.getQuestionById(QuestionIdentity(questionId))

        return QuestionDto(
                question.identity.id,
                question.author.identity.id,
                question.title,
                question.description,
                question.answers.map {
                    AnswerDto(it.identity.id, it.author.identity.id, it.description)
                })
    }

    fun answerQuestion(questionId : UUID, answerDto : AnswerDto) {
        val author = authorRepository.getAuthorById(ContributorIdentity(answerDto.authorId))
        val question = questionRepository.getQuestionById(QuestionIdentity(questionId))

        val answer = Answer(
            AnswerIdentity(answerDto.id),
            question.author.identity,
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