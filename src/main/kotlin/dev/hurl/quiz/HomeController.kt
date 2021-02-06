package dev.hurl.quiz

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class HomeController {

    // https://lovattspuzzles.com/online-puzzles-competitions/ultimate-online-trivia-quiz/
    @GetMapping(path = ["/"])
    fun get(): ModelAndView {
        val modelAndView = ModelAndView("home")
        modelAndView["name"] = "toto"
        return modelAndView
    }
}

