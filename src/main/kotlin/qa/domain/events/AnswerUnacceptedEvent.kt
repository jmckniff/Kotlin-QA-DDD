package qa.domain.events

import qa.domain.entities.Answer
import ValueObjects.DomainEvent

class AnswerUnacceptedEvent(val answer: Answer) : DomainEvent() {

}
