package qa.domain.events

import qa.domain.entities.Answer
import qa.domain.valueObjects.DomainEvent

class AnswerAcceptedEvent(val answer: Answer) : DomainEvent() {

}