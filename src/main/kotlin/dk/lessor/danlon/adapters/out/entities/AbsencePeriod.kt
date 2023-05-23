package dk.lessor.danlon.adapters.out.entities

object AbsencePeriod : IntIdTable("absence.absence_period", "id") {
    val idCompany = text("id_company")
    val idEmployee = text("id_employee")
    val idAbsenceType = reference("id_absence_type", AbsenceType, onDelete = ReferenceOption.NO_ACTION)
    val startDate = date("start_date")
    val endDate = date("end_date")
    val softDeleteDateTime = datetime("soft_delete_date_time").nullable()
    val idRegistration = varchar("id_registration", 10).nullable()
}

class AbsencePeriodEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AbsencePeriodEntity>(AbsencePeriod)

    var idCompany by AbsencePeriod.idCompany
    var idEmployee by AbsencePeriod.idEmployee
    var idAbsenceType by AbsencePeriod.idAbsenceType
    var startDate by AbsencePeriod.startDate
    var endDate by AbsencePeriod.endDate
    var softDeleteDateTime by AbsencePeriod.softDeleteDateTime
    val absenceType by AbsenceTypeEntity referencedOn AbsencePeriod.idAbsenceType
    val absenceDays by AbsenceDayEntity referrersOn AbsenceDay.idPeriod
    val idRegistration by AbsencePeriod.idRegistration
}