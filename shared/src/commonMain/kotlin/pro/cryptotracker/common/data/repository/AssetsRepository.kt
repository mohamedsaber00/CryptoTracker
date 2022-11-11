package pro.cryptotracker.common.data.repository

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.cryptotracker.common.data.local.model.AssetsEntity
import pro.cryptotracker.common.data.remote.AssetsApi
import pro.cryptotracker.common.data.remote.model.NetworkAssets

class AssetsRepository : KoinComponent {

    private val assetsApi: AssetsApi by inject()
    private val realm: Realm by inject()


    @NativeCoroutineScope
    private val mainScope: CoroutineScope = MainScope()

    private val _assetsList = MutableStateFlow<List<NetworkAssets>>(emptyList())
    val assetsList: StateFlow<List<NetworkAssets>> = _assetsList


    private suspend fun getAllAssets(): Flow<ResultsChange<AssetsEntity>> {
        Logger.d("remote fetched ")
        val networkAssets: List<NetworkAssets> = assetsApi.getAllAssets().assetsList
        realm.write {
            networkAssets.forEach { asset ->
                copyToRealm(AssetsEntity().apply {
                    id = asset.id
                    rank = asset.rank
                    symbol = asset.symbol
                    name = asset.name
                    supply = asset.supply
                    maxSupply = asset.maxSupply
                    marketCapUsd = asset.marketCapUsd
                    volumeUsd24Hr = asset.volumeUsd24Hr
                    priceUsd = asset.priceUsd
                    changePercent24Hr = asset.changePercent24Hr
                    vwap24Hr = asset.vwap24Hr
                })
            }
        }

        return realm.query<AssetsEntity>().asFlow()
    }
}