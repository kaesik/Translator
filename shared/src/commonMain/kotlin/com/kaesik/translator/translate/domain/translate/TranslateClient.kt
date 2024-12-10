package com.kaesik.translator.translate.domain.translate

import com.kaesik.translator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}
