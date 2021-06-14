package dev.hurl.quiz.controller.newquiz

data class NewQuizDto (
    val allQuestions: List<QuestionDto>,
    val questions: List<ChoiceDto>
)