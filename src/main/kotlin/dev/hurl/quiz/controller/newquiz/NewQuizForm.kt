package dev.hurl.quiz.controller.newquiz

import javax.validation.constraints.Email
import javax.validation.constraints.Size


data class NewQuizForm(
    @field:Size(min = 4, max = 32)
    val name: String,
    @field:Email
    val email: String?,
    val question0: String,
    val question1: String,
    val question2: String,
    val question3: String,
    val question4: String,
)