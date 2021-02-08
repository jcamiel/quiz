package dev.hurl.quiz.model

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val title: String,
    val correct: Boolean
)
