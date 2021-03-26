package dev.hurl.quiz.repository

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Sort
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository
import org.springframework.util.ResourceUtils
import kotlin.math.min

@Repository
class QuestionRepository(
    private val resourceLoader: ResourceLoader
) {
    private val questions: MutableList<Question>

    init {
        val file = resourceLoader.getResource("classpath:quiz.json").inputStream
        val data = file.readAllBytes().decodeToString()
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