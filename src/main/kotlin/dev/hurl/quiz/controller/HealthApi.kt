package dev.hurl.quiz.controller

import dev.hurl.quiz.model.Health
import dev.hurl.quiz.model.Status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class HealthApi {

    @GetMapping(value = ["/health"])
    fun get(): Health {
        return Health(status = Status.RUNNING, reportedDate = Date() )
    }

}