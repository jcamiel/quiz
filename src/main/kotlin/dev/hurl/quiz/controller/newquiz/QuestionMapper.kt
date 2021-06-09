package dev.hurl.quiz.controller.newquiz

import dev.hurl.quiz.model.Question

fun Question.toQuestionDto(): QuestionDto {
    return QuestionDto(title=title, id=id)

}