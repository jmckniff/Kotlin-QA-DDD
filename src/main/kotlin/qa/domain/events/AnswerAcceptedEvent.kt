package Events

import qa.domain.entities.Answer
import ValueObjects.DomainEvent

class AnswerAcceptedEvent(val answer: Answer) : DomainEvent() {

}