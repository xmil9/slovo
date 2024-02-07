package net.mikelindner.slovo

// Abstracts the direction of a translation.
interface Translation {
    val direction: String
    val fromLanguage: String
    fun from(w: Word): String
    val toLanguage: String
    fun to(w: Word): String
}

val toEnglish: String = "en"
val toRussian: String = "ru"

class EnglishTranslation : Translation {
    override val direction: String = toEnglish

    override val fromLanguage: String = "Russian";
    override fun from(w: Word): String {
        return w.ru
    }

    override val toLanguage: String = "English";
    override fun to(w: Word): String {
        return w.en;
    }
}

class RussianTranslation : Translation {
    override val direction: String = toRussian

    override val fromLanguage: String = "English";
    override fun from(w: Word): String {
        return w.en
    }

    override val toLanguage: String = "Russian";
    override fun to(w: Word): String {
        return w.ru;
    }
}
