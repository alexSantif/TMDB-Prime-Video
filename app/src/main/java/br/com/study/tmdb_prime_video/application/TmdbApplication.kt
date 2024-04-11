package br.com.study.tmdb_prime_video.application

import android.app.Application
import br.com.study.tmdb_prime_video.core.di.NetworkModule
import br.com.study.tmdb_prime_video.home.di.HomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TmdbApplication)
            androidLogger()
            modules(HomeModule.instance, NetworkModule.instance)
        }
    }
}