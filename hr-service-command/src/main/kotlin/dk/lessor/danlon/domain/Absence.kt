package dk.lessor.danlon.domain

import dk.lessor.danlon.command.CreateAbsence
import dk.lessor.danlon.events.CreateAbsenceCreated
import io.vavr.collection.Seq
import io.vavr.control.Either

data class Absence {

    val idAbsence: Int?,
    var idCompany: String,
    var idEmployee: String,
    var idAbsenceType: Int,
    var absenceType: AbsenceType?,
    var startDate: String?,
    var endDate: String?,
    var dayRowList: MutableList<AbsenceDayRow>,
    var employeeName: String? = null,
    var employeeNumber: String? = null,
    var transferStatus: AbsenceWiredStatusEnum? = null,
    val idRegistration: String? = null,
    val isFromApp: Boolean = idRegistration != null

    companion object {
        fun apply(command: CreateAbsence?): Either<Seq<Error?>?, CreateAbsenceCreated?>? {
            val validation: Unit = CreateAbsenceValidator().validate(command)
            return if (validation.isInvalid()) Either.left(
                validation.getError().get()
            ) else  Either.right(CreateAbsenceCreated(Absence()))
        }
    }
}

