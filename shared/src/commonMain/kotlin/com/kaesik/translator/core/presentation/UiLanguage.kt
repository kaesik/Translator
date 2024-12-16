package com.kaesik.translator.core.presentation

import com.kaesik.translator.core.domain.language.Language

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}
