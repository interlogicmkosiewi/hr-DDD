package dk.lessor.danlon.adapters.out.jpa

import dk.lessor.danlon.domain.Absence
import dk.lessor.danlon.port.out.SaveAbsencePort

class SaveAbsenceAdapter : SaveAbsencePort{
    override fun save(absence: Event<Absence>?) {
        // Save enitity
    }
}