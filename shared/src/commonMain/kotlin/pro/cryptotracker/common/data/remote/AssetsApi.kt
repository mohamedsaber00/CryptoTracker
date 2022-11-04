package pro.cryptotracker.common.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import pro.cryptotracker.common.Util.ASSETS_PARAM
import pro.cryptotracker.common.Util.BASE_URL
import pro.cryptotracker.common.data.model.AssetsResponse

class AssetsApi(
    private val client: HttpClient,
) : KoinComponent {
    /*
    * Get All Assets
    * */
    suspend fun getAllAssets() = client.get(ASSETS_PARAM).body<AssetsResponse>()

    /*
    * Get Single Assets by id
    * */
    suspend fun getAssetById(id : Int) = client.get("$ASSETS_PARAM/$id")
}

