package com.kaesik.translator.translate.presentation

import com.kaesik.translator.core.domain.util.Resource
import com.kaesik.translator.core.domain.util.toCommonStateFlow
import com.kaesik.translator.core.presentation.UiLanguage
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.translate.Translate
import com.kaesik.translator.translate.domain.translate.TranslateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(TranslateState())
    val state = combine(
        _state,
        historyDataSource.getHistory(context = viewModelScope.coroutineContext)
    ) { state, history ->
        if (state.history != history) {
            state.copy(
                history = history.mapNotNull {
                UiHistoryItem(
                    id = it.id ?: return@mapNotNull null,
                    fromText = it.fromText,
                    toText = it.toText,
                    fromLanguage = UiLanguage.byCode(it.fromLanguageCode),
                    toLanguage = UiLanguage.byCode(it.toLanguageCode)
                )
            })
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState())
        .toCommonStateFlow()

    private var translateJob: Job? = null

    fun onEvent(event: TranslateEvent) {
        when (event) {
            is TranslateEvent.ChangeTranslationText -> {
                _state.update { it.copy(
                    fromText = event.text
                ) }
            }
            is TranslateEvent.ChooseFromLanguage -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = true,
                    fromLanguage = event.language
                ) }
            }
            is TranslateEvent.ChooseToLanguage -> {
                val newState = _state.updateAndGet { it.copy(
                    isChoosingToLanguage = true,
                    toLanguage = event.language
                ) }
                translate(newState)
            }
            TranslateEvent.CloseTranslation -> {
                _state.update { it.copy(
                    isTranslating = false,
                    fromText = "",
                    toText = null,
                ) }
            }
            TranslateEvent.EditTranslation -> {
                if (state.value.toText != null) {
                    _state.update { it.copy(
                        isTranslating = false,
                        toText = null
                    ) }
                }
            }
            TranslateEvent.OnErrorSeen -> {
                _state.update { it.copy(
                    error = null
                ) }
            }
            TranslateEvent.OpenFromLanguageDropDown -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = true
                ) }
            }
            TranslateEvent.OpenToLanguageDropDown -> {
                _state.update { it.copy(
                    isChoosingToLanguage = true
                ) }
            }
            is TranslateEvent.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update { it.copy(
                    fromText = event.item.fromText,
                    toText = event.item.toText,
                    isTranslating = false,
                    fromLanguage = event.item.fromLanguage,
                    toLanguage = event.item.toLanguage
                ) }
            }
            TranslateEvent.StopChoosingLanguage -> {
                _state.update { it.copy(
                    isChoosingFromLanguage = false,
                    isChoosingToLanguage = false
                ) }
            }
            is TranslateEvent.SubmitVoiceResult -> {
                _state.update { it.copy(
                    fromText = event.result ?: it.fromText,
                    isTranslating = if (event.result != null) false else it.isTranslating,
                    toText = if (event.result != null) null else it.toText
                ) }
            }
            TranslateEvent.SwapLanguages -> {
                _state.update { it.copy(
                    fromLanguage = it.toLanguage,
                    toLanguage = it.fromLanguage,
                    fromText = it.toText ?: "",
                    toText = if (it.toText != null) it.fromText else null
                ) }
            }
            TranslateEvent.Translate -> translate(state.value)
            else -> Unit
        }
    }

    private fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank()) return

        translateJob = viewModelScope.launch {
            _state.update { it.copy(isTranslating = true) }

            val result = translate.execute(
            fromLanguage = state.fromLanguage.language,
            fromText = state.fromText,
            toLanguage = state.toLanguage.language
        )
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(
                        isTranslating = false,
                        toText = result.data
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(
                        isTranslating = false,
                        error = (result.throwable as? TranslateException)?.error
                    ) }
                }
            }
        }
    }
}