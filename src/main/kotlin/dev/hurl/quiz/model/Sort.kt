package dev.hurl.quiz.model

sealed class Sort : Comparator<Question> {
    companion object {
        fun fromParameter(param: String): Sort? {
            return when (param) {
                "newest" -> NewestSort
                "oldest" -> OldestSort
                "stars" -> StarsSort
                else -> null
            }
        }
    }
}

object NewestSort : Sort() {
    override fun compare(a: Question, b: Question): Int {
        return when {
            a.created == b.created -> 0
            a.created.after(b.created) -> -1
            else -> 1
        }
    }
}

object OldestSort: Sort() {
    override fun compare(a: Question, b: Question): Int {
        return when {
            a.created == b.created -> 0
            a.created.before(b.created) -> -1
            else -> 1
        }
    }
}

object StarsSort: Sort() {
    override fun compare(a: Question, b: Question): Int {
        return when {
            a.stars == b.stars -> 0
            a.stars > b.stars -> -1
            else -> 1
        }
    }
}
