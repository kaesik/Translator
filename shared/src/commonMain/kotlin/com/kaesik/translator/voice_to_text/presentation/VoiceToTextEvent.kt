package com.kaesik.translator.voice_to_text.presentation

sealed class VoiceToTextEvent {
    data object Close: VoiceToTextEvent()
    data class PermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDisplayState: DisplayState,
    ): VoiceToTextEvent()
    data class ToggleRecording(val languageCode: String): VoiceToTextEvent()
    data object Reset: VoiceToTextEvent()
}
