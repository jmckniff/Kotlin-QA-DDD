package qa.services.dto

import java.util.*

data class Question(val id : UUID, val authorId : UUID, val title : String, val description : String)