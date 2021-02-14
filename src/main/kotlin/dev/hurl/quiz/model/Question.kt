package dev.hurl.quiz.model

import dev.hurl.quiz.helper.Rfc3339Serializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Question(
    val id: String,
    @Serializable(with = Rfc3339Serializer::class)
    val created: Date,
    val title: String,
    val stars: Int,
    val answers: List<Answer>
)
