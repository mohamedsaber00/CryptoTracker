package pro.cryptotracker.android

import android.app.Application
import android.util.Log
import co.touchlab.kermit.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.logger.Level
import pro.cryptotracker.common.di.appModule
import pro.cryptotracker.common.di.initKoin

class CryptoTrackerApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            // https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CryptoTrackerApplication)
        }
    }
}