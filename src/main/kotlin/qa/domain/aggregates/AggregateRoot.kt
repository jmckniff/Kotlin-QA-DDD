package aggregates

import ValueObjects.DomainEvent

open class AggregateRoot {
    private val _queuedEvents = mutableListOf<DomainEvent>()

    val queuedEvents : List<DomainEvent> = _queuedEvents

    fun queueEvent(event : DomainEvent) {
        _queuedEvents.add(event)
    }

}