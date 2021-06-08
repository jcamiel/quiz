package dev.hurl.quiz.controller.newquiz

import dev.hurl.quiz.helper.set
import dev.hurl.quiz.model.NewestSort
import dev.hurl.quiz.service.QuestionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class NewQuizController(
    private val questionService: QuestionService
){

    @GetMapping(path = ["/new-quiz"])
    fun get(): ModelAndView {
        val modelAndView = ModelAndView("new-quiz")
        modelAndView["dto"] = getDto()
        return modelAndView
    }

    @PostMapping(path = ["/new-quiz"])
    fun post(): ModelAndView {
        return ModelAndView("redirect:/")
    }

    private fun getDto(): NewQuizDto {
        val size = questionService.getQuestionsCount()
        val questions = questionService.getQuestions(offset = 0, size = size, sort = NewestSort)
        return NewQuizDto(questions = questions.map { QuestionDto(title = it.title) })
    }
}