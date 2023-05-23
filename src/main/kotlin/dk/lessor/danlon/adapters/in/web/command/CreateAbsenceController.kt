package dk.lessor.danlon.adapters.`in`.web.command

import dk.lessor.danlon.port.`in`.CreateAbsenceUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@RestController
@RequestMapping("/v1")
class CreateAbsenceController(private val createAbsenceUseCase: CreateAbsenceUseCase) {

    @PostMapping("/absence")
    fun createAbsence(@RequestBody createAbsenceDto: CreateAbsenceDto): Response? {
        return createAbsenceUseCase.create(CreateAbsenceDtoMapper.INSTANCE.map(createAbsenceDto))
            .fold(
                { error ->
                    Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                            ErrorResponse.from(
                                Collections.singletonMap(
                                    "Some errros"
                                ),
                                error
                            )
                        ).build()
                }
            ) { result -> Response.status(Response.Status.CREATED).build() }
    }
}