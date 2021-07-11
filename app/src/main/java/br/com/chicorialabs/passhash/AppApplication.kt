package br.com.chicorialabs.passhash

import android.app.Application
import br.com.chicorialabs.passhash.di.repositoryModule
import br.com.chicorialabs.passhash.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val TAG = "PassHash"
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }
}