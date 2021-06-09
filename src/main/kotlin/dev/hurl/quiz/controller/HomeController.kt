package dev.hurl.quiz.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {

    @GetMapping(path = ["/"])
    fun get(): ModelAndView {
        return ModelAndView("home")
    }
}