package dev.hurl.quiz.repository

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Sort
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Repository
import org.springframework.util.ResourceUtils
import kotlin.math.min

@Repository
class QuestionRepository {
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
    fun getQuestions(offset: Int, size: Int, sort: Sort): List<Question> {
        val fromIndex = min(offset, questions.size - 1)
        val toIndex = min(offset + size, questions.size)
        return questions
            .sortedWith(sort)
            .subList(fromIndex = fromIndex, toIndex = toIndex)
            .toList()
    }
}