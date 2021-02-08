package dev.hurl.quiz.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import dev.hurl.quiz.helper.set

@Controller
class HomeController {

    @GetMapping(path = ["/"])
    fun get(): ModelAndView {
        val modelAndView = ModelAndView("home")
        modelAndView["name"] = "toto"
        return modelAndView
    }
}