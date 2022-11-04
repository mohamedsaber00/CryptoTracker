package pro.cryptotracker.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Asset(
    var id: String,
    var rank: String?,
    var symbol: String?,
    var name: String?,
    var supply: String?,
    var maxSupply: String?,
    var marketCapUsd: String?,
    var volumeUsd24Hr: String?,
    var priceUsd: String?,
    var changePercent24Hr: String?,
    var vwap24Hr: String?
)

@Serializable
data class AssetsResponse(
   @SerialName("data") val assetsList : List<Asset>
)



