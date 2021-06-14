package dev.hurl.quiz.controller.newquiz

data class NewQuizFormDto (
    val name: String,
    val email: String?,
    val question0: String,
    val question1: String,
    val question2: String,
    val question3: String,
    val question4: String,
)