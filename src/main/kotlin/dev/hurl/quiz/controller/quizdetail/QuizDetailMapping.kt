package dev.hurl.quiz.controller.quizdetail

class QuizDetailUrlMapping {
    companion object {

        fun getRelativeUrlMapping(id: String): String {
            return "/quiz/detail/$id"
        }
    }
}