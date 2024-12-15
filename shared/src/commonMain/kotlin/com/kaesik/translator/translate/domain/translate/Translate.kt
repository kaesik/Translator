package com.kaesik.translator.translate.domain.translate

import com.kaesik.translator.core.domain.language.Language
import com.kaesik.translator.core.domain.util.Resource
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.history.HistoryItem

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String> {
        return try {
            val translation = client.translate(fromLanguage, fromText, toLanguage)
            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translation
                )
            )
            Resource.Success(translation)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
