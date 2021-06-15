package dev.hurl.quiz.repository

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Quiz
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class QuizRepository(
    private val quizzes: MutableList<Quiz>
){
    /**
     * Create a new quiz
     * @param author name of the creator of this quiz
     * @param email email of the creator of this quiz
     * @param questions questions of this quiz
     * @return a new quiz
     */
    fun createQuiz(author: String, email: String?, questions: List<Question>): Quiz {
        val uuid = UUID.randomUUID()
        val id = uuid.toString().substringBefore("-")
        val quiz = Quiz(
            id = id,
            author = author,
            email = email,
            questions = questions
        )
        quizzes.add(quiz)
        return quiz
    }

    /**
     * Find a quiz given it's id or null
     * @param id quiz id
     * @return a quiz if found or null
     */
    fun findQuizById(id: String): Quiz? {
        return quizzes.firstOrNull { it.id == id }
    }

}