package com.kaesik.translator.di

import com.kaesik.translator.translate.data.local.FakeHistoryDataSource
import com.kaesik.translator.translate.data.remote.FakeTranslateClient
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.translate.Translate
import com.kaesik.translator.translate.domain.translate.TranslateClient
import com.kaesik.translator.voice_to_text.data.FakeVoiceToTextParser
import com.kaesik.translator.voice_to_text.domain.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeTranslateClient(): TranslateClient {
        return FakeTranslateClient()
    }

    @Provides
    @Singleton
    fun provideFakeHistoryDataSource(): HistoryDataSource {
        return FakeHistoryDataSource()
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        historyDataSource: HistoryDataSource
    ): Translate {
        return Translate(
            client = client,
            historyDataSource = historyDataSource
        )
    }

    @Provides
    @Singleton
    fun provideFakeVoiceToTextParser(): VoiceToTextParser {
        return FakeVoiceToTextParser()
    }
}
