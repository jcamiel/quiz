package dev.hurl.quiz.form

data class SelectField (
    val value: String = "",
    val valid: Boolean = true,
    val choices: List<Choice>
) {

    data class Choice (
        val text: String,
        val value: String
    )
}