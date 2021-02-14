package dev.hurl.quiz.model

enum class Sort {
    NEWEST,
    OLDEST,
    STARS;

    companion object {
        fun fromParameter(param: String): Sort? {
            return when (param) {
                "newest" -> NEWEST
                "oldest" -> OLDEST
                "stars" -> STARS
                else -> null
            }
        }
    }
}

