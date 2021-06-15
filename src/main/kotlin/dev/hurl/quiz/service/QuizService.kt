package dev.hurl.quiz.service

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Quiz
import dev.hurl.quiz.repository.QuizRepository
import org.springframework.stereotype.Service


@Service
class QuizService(
    private val quizRepository: QuizRepository
){
    /**
     * Create a new quiz
     * @param author name of the creator of this quiz
     * @param email email of the creator of this quiz
     * @param questions questions of this quiz
     * @return a new quiz
     */
    fun createQuiz(author: String, email: String?, questions: List<Question>): Quiz {
        return quizRepository.createQuiz(author = author, email = email, questions = questions)
    }

    /**
     * Find a quiz given it's id or null
     * @param id quiz id
     * @return a quiz if found or null
     */
    fun findQuizById(id: String): Quiz? {
        return quizRepository.findQuizById(id)
    }

}