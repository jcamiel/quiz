package dev.hurl.quiz.spring

import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView

@Component
class BasicErrorViewResolver : ErrorViewResolver {

    override fun resolveErrorView(request: HttpServletRequest, status: HttpStatus, model: Map<String, Any>): ModelAndView? {
        val viewName = if (status == HttpStatus.NOT_FOUND) {
            "error/404"
        } else {
            "error/default"
        }
        return ModelAndView(viewName)
    }

}