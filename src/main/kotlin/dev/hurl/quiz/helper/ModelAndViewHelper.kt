package dev.hurl.quiz.helper

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.servlet.ModelAndView

operator fun ModelAndView.set(s: String, value: Any?) {
    addObject(s, value)
}

fun ModelAndView.addCsrfToken(request: HttpServletRequest) {
    val csrfToken = request.getAttribute(CsrfToken::class.java.name) as CsrfToken
    addObject("_csrf", csrfToken)
}