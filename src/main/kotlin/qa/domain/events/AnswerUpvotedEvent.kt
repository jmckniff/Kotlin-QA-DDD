package qa.domain.events

import qa.domain.entities.Answer
import qa.domain.valueObjects.Contributor
import qa.domain.valueObjects.DomainEvent

class AnswerUpvotedEvent(val answer: Answer, val contributor: Contributor) : DomainEvent() {

}