package dev.hurl.quiz.controller

import dev.hurl.quiz.helper.statusCode
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class BasicErrorController: ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val statusCode = request.statusCode
        val viewName = if (statusCode == HttpStatus.NOT_FOUND) {
            "error/404"
        } else {
            "error/default"
        }
        return ModelAndView(viewName)
    }

    override fun getErrorPath(): String? {
        return null
    }
}