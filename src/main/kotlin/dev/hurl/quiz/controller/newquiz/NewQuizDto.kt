package dev.hurl.quiz.controller.newquiz

import dev.hurl.quiz.form.SelectField
import dev.hurl.quiz.form.TextField

data class NewQuizDto (
    val name: TextField,
    val email: TextField,
    val questions: List<SelectField>,
)