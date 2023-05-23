package dk.lessor.danlon.ports.`in`

import dk.lessor.danlon.query.ShowAbsenceQuery
import dk.lessor.danlon.results.ShowAbsenceResult
import io.vavr.control.Either

interface ShowAbsenceUseCase {
    fun showAbsence(query: ShowAbsenceQuery?): Either<out Error?, ShowAbsenceResult?>?
}