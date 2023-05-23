package dk.lessor.danlon.ports.out

import dk.lessor.danlon.results.ShowAbsenceResult
import io.vavr.control.Option

interface FindAbsencePort {
    fun findAbsenceResult(id: String?): Option<ShowAbsenceResult?>?

}
