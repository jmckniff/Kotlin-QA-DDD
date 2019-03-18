package qa.services.dto

import java.util.*

data class QuestionDto(val id : UUID, val authorId : UUID, val title : String, val description : String)