package com.kaesik.translator.android.core.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kaesik.translator.android.R

val SfProText = FontFamily(
    Font(
        resId = R.font.sf_pro_text_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.sf_pro_text_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.sf_pro_text_bold,
        weight = FontWeight.Bold
    ),
)
