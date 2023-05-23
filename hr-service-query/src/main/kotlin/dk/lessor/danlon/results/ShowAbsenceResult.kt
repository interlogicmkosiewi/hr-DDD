package dk.lessor.danlon.results

class ShowAbsenceResult {
    var idAbsence: Int?,
    val idCompany: String,
    val idEmployee: String,
    val idAbsenceType: Int,
    val absenceType: AbsenceType?,
    val startDate: String?,
    val endDate: String?,
    var dayRowList: MutableList<AbsenceDayRow>,
    var employeeName: String? = null,
    var employeeNumber: String? = null,
    var transferStatus: AbsenceWiredStatusEnum? = null,
    val idRegistration: String? = null,
    val isFromApp: Boolean = idRegistration != null
}