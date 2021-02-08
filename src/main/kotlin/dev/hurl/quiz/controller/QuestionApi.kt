package dev.hurl.quiz.controller

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.repository.QuestionRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class QuestionApi(
    private val questionRepository: QuestionRepository
){

    @GetMapping(value = ["/questions"])
    fun get(
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int
    ): List<Question> {
        return questionRepository.getQuestions(offset = offset, size = size)
    }

}