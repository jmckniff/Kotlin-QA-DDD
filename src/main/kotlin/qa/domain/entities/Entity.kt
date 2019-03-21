package qa.domain.entities

import qa.domain.valueObjects.DomainEvent

open class Entity {
    private val _queuedEvents = mutableListOf<DomainEvent>()

    val queuedEvents : List<DomainEvent> = _queuedEvents

    fun queueEvent(event : DomainEvent) {
        _queuedEvents.add(event)
    }
}