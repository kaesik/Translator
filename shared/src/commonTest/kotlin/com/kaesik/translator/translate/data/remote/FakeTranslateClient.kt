package com.kaesik.translator.translate.data.remote

import com.kaesik.translator.core.domain.language.Language
import com.kaesik.translator.translate.domain.translate.TranslateClient

class FakeTranslateClient: TranslateClient {

    var translatedText = "translated"

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        return translatedText
    }
}
