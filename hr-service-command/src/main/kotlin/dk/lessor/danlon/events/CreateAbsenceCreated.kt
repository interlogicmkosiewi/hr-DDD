package dk.lessor.danlon.events

import dk.lessor.danlon.domain.Absence

class CreateAbsenceCreated(private val absence: Absence) : Event<Absence> {

    override fun get(): Absence {
        return absence
    }

}