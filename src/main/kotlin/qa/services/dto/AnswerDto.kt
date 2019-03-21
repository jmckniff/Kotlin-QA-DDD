package qa.services.dto

import java.util.*

data class AnswerDto(val id: UUID, val authorId: UUID, val description: String, val accepted: Boolean, val votes: Int) {

}