package dev.hurl.quiz.service

import dev.hurl.quiz.model.Question
import dev.hurl.quiz.model.Sort
import dev.hurl.quiz.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val repository: QuestionRepository
) {

    /**
     * Returns a list of questions
     * @param offset offset for the returned sublist
     * @param size size of the returned sublist
     * @return sublist of Question
     */
    fun getQuestions(offset: Int, size: Int, sort: Sort): List<Question> {
        return repository.getQuestions(offset = offset, size = size, sort = sort)
    }

    /**
     * Returns the number of questions in the repository
     */
    fun getQuestionsCount(): Int = repository.getQuestionsCount()

    /**
     * Returns a question at the specified index
     * @param index index of the question
     * @return a Question
     */
    fun getQuestion(index: Int): Question = repository.getQuestion(index)

}