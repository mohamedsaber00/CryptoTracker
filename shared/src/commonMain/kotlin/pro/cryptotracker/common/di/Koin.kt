package pro.cryptotracker.common.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.client.utils.buildHeaders
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import pro.cryptotracker.common.Util.API_KEY
import pro.cryptotracker.common.Util.BASE_URL
import pro.cryptotracker.common.data.remote.AssetsApi

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule(enableNetworkLogs = enableNetworkLogs))

    }
}


fun appModule(enableNetworkLogs: Boolean) = module {
    single { createJson() }
    single { createHttpClient(get(), enableNetworkLogs = enableNetworkLogs) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob() ) }
    single { AssetsApi(get()) }
}



fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }


fun createHttpClient(json: Json, enableNetworkLogs: Boolean) = HttpClient {


    defaultRequest {
        url(BASE_URL)
        headers {
       //     append(HttpHeaders.Authorization, "Bearer $API_KEY")

        }
    }
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}