package br.com.victorcs.trellochallenge

import android.app.Application
import br.com.victorcs.trellochallenge.di.TrelloInitialization
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class TrelloChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
        setUpTimber()
    }

    private fun setUpKoin() =
        startKoin {
            androidLogger()
            androidContext(this@TrelloChallengeApplication)
            modules(TrelloInitialization().init())
        }

    private fun setUpTimber() =
        Timber.plant(Timber.DebugTree())

}
