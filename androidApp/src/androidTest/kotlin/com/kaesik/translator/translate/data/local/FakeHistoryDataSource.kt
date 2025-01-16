package com.kaesik.translator.translate.data.local

import com.kaesik.translator.core.domain.util.CommonFlow
import com.kaesik.translator.core.domain.util.toCommonFlow
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.history.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class FakeHistoryDataSource: HistoryDataSource {

    private val _data = MutableStateFlow<List<HistoryItem>>(emptyList())

    override fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return _data.toCommonFlow()
    }

    override suspend fun insertHistoryItem(historyItem: HistoryItem) {
        _data.value += historyItem
    }
}
