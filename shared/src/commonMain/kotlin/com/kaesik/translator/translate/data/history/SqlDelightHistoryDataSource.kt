package com.kaesik.translator.translate.data.history

import com.kaesik.translator.core.domain.util.CommonFlow
import com.kaesik.translator.database.TranslateDatabase
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.history.HistoryItem

class SqlDelightHistoryDataSource(
    database: TranslateDatabase,
): HistoryDataSource {

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertHistoryItem(historyItem: HistoryItem) {
        TODO("Not yet implemented")
    }
}
