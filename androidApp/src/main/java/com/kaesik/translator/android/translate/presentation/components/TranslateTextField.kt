package com.kaesik.translator.android.translate.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kaesik.translator.core.presentation.UiLanguage

@Composable
fun TranslateTextField(
    fromText: String,
    toText: String?,
    isTranslating: Boolean,
    fromLanguage: UiLanguage,
    toLanguage: UiLanguage,
    onTranslateClick: () -> Unit,
    onTextChanged: (String) -> Unit,
    onCopyClick: (String) -> Unit,
    onSpeakerClick: () -> Unit,
    onTextFieldClick: () -> Unit,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun IdleTranslateTextField(
    fromText: String,
    isTranslating: Boolean,
    onTranslateClick: () -> Unit,
    onTextChanged: (String) -> Unit,
    modifier: Modifier
) {

}
