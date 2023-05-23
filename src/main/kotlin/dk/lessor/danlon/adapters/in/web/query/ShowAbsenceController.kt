package dk.lessor.danlon.adapters.`in`.web.query

import dk.lessor.danlon.ports.`in`.ShowAbsenceUseCase
import dk.lessor.danlon.query.ShowAbsenceQuery
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.Response

@RestController
@RequestMapping("/v1/absence")
class ShowAbsenceController(private val showAbsenceUseCase: ShowAbsenceUseCase) {

    @GetMapping(
        "/{idAbsence}",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun showAbsence(@PathVariable idAbsence: String): Response {
        return showAbsenceUseCase.showAbsence(ShowAbsenceQuery(idAbsence))!!.fold(
            { error ->
                Response.status(Response.Status.NOT_FOUND).entity(error).build()
            },
            { result -> Response.ok(result).build() }
        )
    }
}