package com.kaesik.translator.translate.domain.history

import com.kaesik.translator.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(historyItem: HistoryItem)
}
