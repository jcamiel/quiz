package dev.hurl.quiz.helper

import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest

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