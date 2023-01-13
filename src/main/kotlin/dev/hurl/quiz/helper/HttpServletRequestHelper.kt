package dev.hurl.quiz.helper

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

val HttpServletRequest.statusCode: HttpStatus
get() {
    val statusCode = getAttribute("javax.servlet.error.status_code") as Int?
    return if (statusCode == null) {
        HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
        try {
            HttpStatus.valueOf(statusCode)
        } catch (e: Exception) {
            HttpStatus.INTERNAL_SERVER_ERROR
        }
    }
}