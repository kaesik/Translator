package com.kaesik.translator.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.kaesik.translator.database.TranslateDatabase
import com.kaesik.translator.translate.data.history.SqlDelightHistoryDataSource
import com.kaesik.translator.translate.data.local.DatabaseDriverFactory
import com.kaesik.translator.translate.data.remote.HttpClientFactory
import com.kaesik.translator.translate.data.translate.KtorTranslateClient
import com.kaesik.translator.translate.domain.history.HistoryDataSource
import com.kaesik.translator.translate.domain.translate.Translate
import com.kaesik.translator.translate.domain.translate.TranslateClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslationClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriverFactory(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }
}
