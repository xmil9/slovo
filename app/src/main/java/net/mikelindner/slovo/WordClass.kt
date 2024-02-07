package net.mikelindner.slovo

sealed class WordClass(val name: String, val display: String, val description: String) {
    data object Adjective: WordClass(
        "adjective",
        "Adjective",
        "Describes features or qualities of nouns and pronouns."
    )
    data object Adverb: WordClass(
        "adverb",
        "Adverb",
        "Adds information about a verb, an adjective, another adverb or a clause or a " +
                "whole sentence."
    )
    data object Conjunction: WordClass(
        "conjunction",
        "Conjunction",
        "Shows a link between one word, phrase or clause and another. Examples: " +
                "and, but, when, if, because"
    )
    data object Determiner: WordClass(
        "determiner",
        "Determiner",
        "Comes before nouns. They show what type of reference the noun is making. Examples: " +
                " a/an, the, my, his, some, this, both"
    )
    data object Expression: WordClass(
        "expression",
        "Expression",
        "Idiomatic way of saying something. Examples: " +
                "too bad"
    )
    data object Interjection: WordClass(
        "interjection",
        "Interjection",
        "Mostly exclamation word, which shows people’s reactions to events and situations. " +
                "Examples: hello, oh, wow"
    )
    data object Noun: WordClass(
        "noun",
        "Noun",
        "Refers to a person, animal or thing."
    )
    data object Preposition: WordClass(
        "preposition",
        "Preposition",
        "Describes the relationship between words from the major word classes. Examples: " +
                "at, in, on, across, behind, for"
    )
    data object Pronoun: WordClass(
        "pronoun",
        "Pronoun",
        "Substitutes for a noun phrases. Examples: " +
                "you, it, we, mine, ours, theirs, someone, anyone, one, this, those"
    )
    data object Verb: WordClass(
        "verb",
        "Verb",
        "Refers to an action, event or state."
    )

    companion object {
        fun find(text: String): WordClass? {
            return wordClasses.find { wc -> wc.name == text || wc.display == text }
        }
    }
}

val wordClasses = listOf(
    WordClass.Adjective,
    WordClass.Adverb,
    WordClass.Conjunction,
    WordClass.Determiner,
    WordClass.Expression,
    WordClass.Interjection,
    WordClass.Noun,
    WordClass.Preposition,
    WordClass.Pronoun,
    WordClass.Verb,
)
