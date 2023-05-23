package dk.lessor.danlon.adapters.out.jpa

import dk.lessor.danlon.adapters.out.entities.AbsencePeriodEntity
import dk.lessor.danlon.ports.out.FindAbsencePort
import dk.lessor.danlon.results.ShowAbsenceResult
import io.vavr.control.Option

class FindAbsenceAdapter(private val absencePeriodEntity: AbsencePeriodEntity) : FindAbsencePort {

    override fun findAbsenceResult(id: String?): Option<ShowAbsenceResult?>? {
        // find data
        return absencePeriodEntity.findById()
    }
}