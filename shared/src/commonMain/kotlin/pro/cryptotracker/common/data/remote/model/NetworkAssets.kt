package pro.cryptotracker.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/*
* The Network & UI representation of Assets
* TODO : Separate the UI representation to a different class
* */
@Serializable
data class NetworkAssets(
    var id: String?,
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
   @SerialName("data") val assetsList : List<NetworkAssets>
)



