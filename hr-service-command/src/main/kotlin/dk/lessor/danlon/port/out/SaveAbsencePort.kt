package dk.lessor.danlon.port.out

import dk.lessor.danlon.domain.Absence
import dk.lessor.danlon.events.Event

interface SaveAbsencePort {
    fun save(absence: Event<Absence>?)
}