package com.kaesik.translator.translate.presentation

import com.kaesik.translator.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long?,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage,
)
