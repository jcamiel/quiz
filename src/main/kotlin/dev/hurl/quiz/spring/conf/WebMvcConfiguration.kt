package dev.hurl.quiz.spring.conf

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
internal class WebMvcConfiguration : WebMvcConfigurer {
    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON)
    }
}