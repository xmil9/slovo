package net.mikelindner.slovo

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        WordService.provide(this)
    }
}