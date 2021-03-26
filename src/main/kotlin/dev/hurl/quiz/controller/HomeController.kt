package dev.hurl.quiz.controller

import dev.hurl.quiz.helper.addCsrfToken
import dev.hurl.quiz.helper.set
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController {

    @GetMapping(path = ["/"])
    fun get(request: HttpServletRequest): ModelAndView {

        val modelAndView = ModelAndView("home")
        modelAndView.addCsrfToken(request)
        modelAndView["title"] = "Welcome to Quiz!"
        modelAndView["name"] = "toto"

        return modelAndView
    }
}