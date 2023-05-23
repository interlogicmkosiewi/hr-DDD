package dk.lessor.danlon.services

import dk.lessor.danlon.command.CreateAbsence
import dk.lessor.danlon.domain.Absence
import dk.lessor.danlon.events.CreateAbsenceCreated
import dk.lessor.danlon.port.`in`.CreateAbsenceUseCase
import dk.lessor.danlon.port.out.SaveAbsencePort
import io.vavr.collection.Seq
import io.vavr.control.Either

class CreateAbsence(private val saveAbsencePort: SaveAbsencePort) : CreateAbsenceUseCase{
    override fun create(command: CreateAbsence?): Either<Seq<Error?>?, CreateAbsenceCreated?>? {
        return Absence.apply(command)?.peek(saveAbsencePort::save)
    }

}