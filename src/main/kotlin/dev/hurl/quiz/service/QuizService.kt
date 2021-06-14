package dev.hurl.quiz.service

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Quiz
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class QuizService(
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

}