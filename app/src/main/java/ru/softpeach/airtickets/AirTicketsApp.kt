package ru.softpeach.airtickets

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.softpeach.airtickets.di.appModule
import ru.softpeach.airtickets.di.dataModule
import ru.softpeach.airtickets.di.dispatcherModule

class AirTicketsApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AirTicketsApp)
            modules(appModule, dataModule, dispatcherModule)
        }
    }
}
