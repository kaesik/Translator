package com.kaesik.translator.translate.data.remote

import io.ktor.client.HttpClient

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HttpClientFactory {
     fun create(): HttpClient
}
