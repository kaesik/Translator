package com.kaesik.translator.android.voice_to_text.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaesik.translator.voice_to_text.domain.VoiceToTextParser
import com.kaesik.translator.voice_to_text.presentation.VoiceToTextEvent
import com.kaesik.translator.voice_to_text.presentation.VoiceToTextViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextParser @Inject constructor(
    private val parser: VoiceToTextParser,
): ViewModel() {

    private val viewModel by lazy {
        VoiceToTextViewModel(
            parser = parser,
            coroutineScope = viewModelScope,
        )
    }

    val state = viewModel.state

    fun onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event)
    }
}
