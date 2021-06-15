package dev.hurl.quiz.controller.quizdetail

import dev.hurl.quiz.helper.set
import dev.hurl.quiz.service.QuizService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.ModelAndView


@Controller
class QuizDetailController(
    private val quizService: QuizService
){

    /**
     * Return the detail page of a Quiz
     * @param id identifier of a quiz
     * @return a ModelAndView
     */
    @GetMapping(path = ["/quiz/detail/{id}"])
    fun getById(@PathVariable id: String): ModelAndView {
        val quiz = quizService.findQuizById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource")
        val dto  = QuizDetailDto(quiz = quiz)
        val modelAndView = ModelAndView("quiz")
        modelAndView["dto"] = dto
        return modelAndView
    }

}