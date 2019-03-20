package qa.domain.events

import qa.domain.entities.Answer
import qa.domain.valueObjects.DomainEvent

class AnswerUnacceptedEvent(val answer: Answer) : DomainEvent() {

}
