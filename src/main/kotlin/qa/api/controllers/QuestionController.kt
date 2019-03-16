package qa.api.controllers

import org.springframework.web.bind.annotation.*
import qa.services.QuestionService
import qa.services.dto.Question
import java.util.*

@RestController
@RequestMapping("/api/question")
class QuestionController(val questionService: QuestionService) {

    @GetMapping("/{authorId}")
    fun getQuestionsByAuthorId(@PathVariable authorId : UUID): List<Question> {

        val questions = questionService.getQuestions(authorId)
        return questions
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST))
    fun createQuestion(@RequestBody question: Question): Question {

        questionService.askQuestion(question)
        return question
    }
}


