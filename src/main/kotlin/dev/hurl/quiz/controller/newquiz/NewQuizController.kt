package dev.hurl.quiz.controller.newquiz

import dev.hurl.quiz.controller.quizdetail.QuizDetailUrlMapping
import dev.hurl.quiz.form.SelectField
import dev.hurl.quiz.form.TextField
import dev.hurl.quiz.helper.addCsrfToken
import dev.hurl.quiz.helper.set
import dev.hurl.quiz.model.NewestSort
import dev.hurl.quiz.service.QuestionService
import dev.hurl.quiz.service.QuizService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
class NewQuizController(
    private val questionService: QuestionService,
    private val quizService: QuizService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping(path = ["/new-quiz"])
    fun get(request: HttpServletRequest): ModelAndView {
        return getModelAndView(request)
    }

    @PostMapping(path = ["/new-quiz"])
    fun post(
        request: HttpServletRequest,
        @ModelAttribute @Valid form: NewQuizForm,
        bindings: BindingResult
    ): ModelAndView {

        if (bindings.hasErrors()) {
            logger.warn("Error $form not valid")
            return getModelAndView(request = request, form = form, errors = bindings)
        }

        // We check that our ids are valid (present in the data base)
        // TODO: check unique values
        val ids = with(form) {
            listOf(question0, question1, question2, question3, question4)
        }
        val questions = questionService.findQuestionByIds(ids)
        if (questions.any { it == null }) {
            logger.warn("Invalid ids $ids")
            return getModelAndView(request = request, form = form)
        }

        val quiz = quizService.createQuiz(
            author = form.name,
            email = form.email,
            questions = questions.filterNotNull()
        )

        val quizDetailUrl = QuizDetailUrlMapping.getRelativeUrlMapping(quiz.id)
        val view = RedirectView(quizDetailUrl)
        return ModelAndView(view)
    }

    /**
     * Return a model and view for the page
     * @param request
     * @param form optional form value
     * @param errors validation errors
     */
    private fun getModelAndView(
        request: HttpServletRequest,
        form: NewQuizForm? = null,
        errors: Errors? = null,
    ): ModelAndView {
        val modelAndView = ModelAndView("new-quiz")

        val size = questionService.getQuestionsCount()
        val allQuestions = questionService.getQuestions(offset = 0, size = size, sort = NewestSort)
        val choices = allQuestions.map { SelectField.Choice(text = it.title, value = it.id) }

        val dto = if (form == null) {
            NewQuizDto(
                name = TextField(),
                email = TextField(),
                questions = (0..4).toList().map {
                    SelectField(choices = choices)
                }
            )
        } else {
            val nameValid = !(errors != null && errors.hasFieldErrors("name"))
            val emailValid = !(errors != null && errors.hasFieldErrors("email"))
            // TODO: fill valid values from validation errors
            NewQuizDto(
                name = TextField(value = form.name, valid = nameValid),
                email = TextField(value = form.email.orEmpty(), valid = emailValid),
                questions = listOf(form.question0, form.question1, form.question2, form.question3, form.question4)
                    .map { SelectField(value = it, valid = true, choices = choices) }
            )
        }

        modelAndView["dto"] = dto
        modelAndView["errors"] = errors
        modelAndView.addCsrfToken(request)
        return modelAndView
    }

}
