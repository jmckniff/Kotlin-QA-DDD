package qa.api.controllers

import org.springframework.web.bind.annotation.*
import qa.services.QuestionService
import qa.services.dto.AnswerDto
import qa.services.dto.QuestionDto
import java.util.*

@RestController
@RequestMapping("/api/question")
class QuestionController(val questionService: QuestionService) {

    @RequestMapping("/Author/{authorId}")
    fun getQuestionsByAuthorId(@PathVariable authorId : UUID): List<QuestionDto> {

        val questions = questionService.getQuestions(authorId)
        return questions
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST))
    fun createQuestion(@RequestBody questionDto: QuestionDto): QuestionDto {

        questionService.askQuestion(questionDto)
        return questionDto
    }

    @GetMapping("/{questionId}")
    fun getQuestionsById(@PathVariable questionId : UUID): QuestionDto {

        val questions = questionService.getQuestion(questionId)
        return questions
    }

    @RequestMapping(value = "/{questionId}/Answer", method = arrayOf(RequestMethod.POST))
    fun answerQuestion(@PathVariable questionId : UUID, @RequestBody answerDto: AnswerDto): AnswerDto {

        questionService.answerQuestion(questionId, answerDto)
        return answerDto
    }
}


