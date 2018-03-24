package com.vocalabs.egtest.example.kotlin

import com.vocalabs.egtest.annotation.Eg
import com.vocalabs.egtest.annotation.EgMatch
import com.vocalabs.egtest.annotation.EgNoMatch

object KotlinExample {

    @EgMatch("dleppik@vocalabs.com")
    @EgNoMatch("David Leppik <dleppik@vocalabs.com>")
    val SIMPLE_EMAIL_RE: Regex = """^[\w+.\-=&|/?!#$*]+@[\w.\-]+\.[\w]+$""".toRegex()

    /*
    @EgMatch("dleppik@vocalabs.com")
    fun matchesEmail(s: String): Boolean = SIMPLE_EMAIL_RE.matches(s)
    */

    /*
    @Eg(given = arrayOf("listOf()"), returns = "listOf()")
    fun vowels(words: Collection<String>): Collection<String> {
        TODO()
    }
    */
}