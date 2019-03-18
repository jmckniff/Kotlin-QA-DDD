package qa.services.dto

import java.util.*

data class AnswerDto(val id : UUID, val questionId : UUID, val authorId : UUID, val description : String) {

}