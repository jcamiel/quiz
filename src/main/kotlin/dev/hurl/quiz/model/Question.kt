package dev.hurl.quiz.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: String,
    val created: String,
    val title: String,
    val stars: Int,
    val answers: List<Answer>
)
