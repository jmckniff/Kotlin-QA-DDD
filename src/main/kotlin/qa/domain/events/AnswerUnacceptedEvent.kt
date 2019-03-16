package qa.domain.events

import Entities.Answer
import ValueObjects.DomainEvent

class AnswerUnacceptedEvent(val answer: Answer) : DomainEvent() {

}
