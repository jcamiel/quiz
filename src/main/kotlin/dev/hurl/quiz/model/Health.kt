package dev.hurl.quiz.model

import dev.hurl.quiz.helper.Rfc3339Serializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
enum class Status {
    STOPPED,
    RUNNING
}

@Serializable
data class Health(
    val status: Status,
    @Serializable(with = Rfc3339Serializer::class)
    val reportedDate: Date,
    val healthy: Boolean,
    val operationId: UInt,
)
