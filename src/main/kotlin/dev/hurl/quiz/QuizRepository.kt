package dev.hurl.quiz

import dev.hurl.quiz.model.Question
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Repository
import org.springframework.util.ResourceUtils


@Repository
class QuizRepository {
    private val questions: MutableList<Question>

    init {
        val file = ResourceUtils.getFile("classpath:quiz.json")
        val data = file.readText()
        questions = Json.decodeFromString(data)
    }

    /**
     * Returns a list of questions
     * @param offset offset for the returned sublist
     * @param size size of the returned sublist
     * @return sublist of Question
     */
    fun getQuestions(offset: Int, size: Int): List<Question> {
        return questions.subList(fromIndex = offset, toIndex = offset + size).toList()
    }
}