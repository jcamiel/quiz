package dev.hurl.quiz.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class NewQuizController {

    @PostMapping(path = ["/new-quiz"])
    fun post(): ModelAndView {
        return ModelAndView("redirect:/")
    }
}
