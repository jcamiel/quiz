package dev.hurl.quiz

import org.springframework.web.servlet.ModelAndView

operator fun ModelAndView.set(s: String, value: Any?) {
    addObject(s, value)
}
