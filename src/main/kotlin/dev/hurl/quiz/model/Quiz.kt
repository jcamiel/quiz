package dev.hurl.quiz.model

data class Quiz(
    val id: String,
    val author: String,
    val email: String?,
    val questions: List<Question>
)
