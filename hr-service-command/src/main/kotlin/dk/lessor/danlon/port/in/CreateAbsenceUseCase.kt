package dk.lessor.danlon.port.`in`

import dk.lessor.danlon.events.CreateAbsenceCreated
import dk.lessor.danlon.command.CreateAbsence
import io.vavr.collection.Seq
import io.vavr.control.Either

interface CreateAbsenceUseCase {
    fun create(command: CreateAbsence?): Either<Seq<Error?>?, CreateAbsenceCreated?>?
}