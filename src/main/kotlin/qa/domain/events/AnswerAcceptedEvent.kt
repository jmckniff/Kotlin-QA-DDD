package Events

import Entities.Answer
import ValueObjects.DomainEvent

class AnswerAcceptedEvent(val answer: Answer) : DomainEvent() {

}