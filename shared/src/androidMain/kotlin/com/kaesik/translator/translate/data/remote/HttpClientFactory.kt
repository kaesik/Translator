package com.kaesik.translator.translate.data.remote

import com.kaesik.translator.AndroidPlatform
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {
     actual fun create(): HttpClient {
            return HttpClient(AndroidPlatform) {
                 install(ContentNegotiation) {
                    json()
                 }
            }
     }
}
