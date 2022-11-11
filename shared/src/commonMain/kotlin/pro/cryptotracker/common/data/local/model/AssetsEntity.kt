package pro.cryptotracker.common.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import pro.cryptotracker.common.data.remote.model.NetworkAssets

class AssetsEntity : RealmObject {
    @PrimaryKey
    var id: String? = ""
    var rank: String? = ""
    var symbol: String? = ""
    var name: String? = ""
    var supply: String? = ""
    var maxSupply: String? = ""
    var marketCapUsd: String? = ""
    var volumeUsd24Hr: String? = ""
    var priceUsd: String? = ""
    var changePercent24Hr: String? = ""
    var vwap24Hr: String? = ""
}


fun AssetsEntity.toNetwork() = NetworkAssets(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    supply = supply,
    maxSupply = maxSupply,
    marketCapUsd = marketCapUsd,
    volumeUsd24Hr = volumeUsd24Hr,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
    vwap24Hr = vwap24Hr
)