package dk.lessor.danlon.service

import dk.lessor.danlon.ports.`in`.ShowAbsenceUseCase
import dk.lessor.danlon.ports.out.FindAbsencePort
import dk.lessor.danlon.query.ShowAbsenceQuery
import dk.lessor.danlon.results.ShowAbsenceResult
import io.vavr.control.Either

class ShowAbsence(private val findAbsencePort: FindAbsencePort) : ShowAbsenceUseCase {

    override fun showAbsence(query: ShowAbsenceQuery?): Either<out Error?, ShowAbsenceResult?>? {
        return findAbsencePort.findAbsenceResult(query?.id)?.fold(
            {
                Either.left(
                    Error(
                        String.format("Must provide 'idAbsence' ${query?.id}")
                    )
                )
            },
            { result -> Either.right(result) }
        )
    }
}