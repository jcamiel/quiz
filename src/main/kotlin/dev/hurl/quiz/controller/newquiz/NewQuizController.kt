package dev.hurl.quiz.controller.newquiz

import dev.hurl.quiz.controller.quizdetail.QuizDetailUrlMapping
import dev.hurl.quiz.helper.addCsrfToken
import dev.hurl.quiz.helper.set
import dev.hurl.quiz.model.NewestSort
import dev.hurl.quiz.service.QuestionService
import dev.hurl.quiz.service.QuizService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpServletRequest

@Controller
class NewQuizController(
    private val questionService: QuestionService,
    private val quizService: QuizService
){
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping(path = ["/new-quiz"])
    fun get(request: HttpServletRequest): ModelAndView {
        return getModelAndView(request)
    }

    @PostMapping(path = ["/new-quiz"])
    fun post(request: HttpServletRequest, @ModelAttribute newQuizFormDto: NewQuizFormDto): ModelAndView {

        val ids = with(newQuizFormDto) {
            listOf(question0, question1, question2, question3, question4)
        }
        val questions = questionService.findQuestionByIds(ids)
        if (questions.any { it == null}) {
           logger.warn("Invalid ids $ids")
           return getModelAndView(request)
        }

        val quiz = quizService.createQuiz(
            author = newQuizFormDto.name,
            email = newQuizFormDto.email,
            questions = questions.filterNotNull()
        )

        val quizDetailUrl = QuizDetailUrlMapping.getRelativeUrlMapping(quiz.id)
        val view = RedirectView(quizDetailUrl)
        return ModelAndView(view)
    }

    private fun getModelAndView(request: HttpServletRequest): ModelAndView {
        val modelAndView = ModelAndView("new-quiz")
        modelAndView["dto"] = getDto()
        modelAndView.addCsrfToken(request)
        return modelAndView
    }

    private fun getDto(): NewQuizDto {
        val size = questionService.getQuestionsCount()
        val questions = questionService.getQuestions(offset = 0, size = size, sort = NewestSort)
        return NewQuizDto(
            allQuestions = questions.map { it.toQuestionDto() },
            questions = listOf()
        )
    }
}