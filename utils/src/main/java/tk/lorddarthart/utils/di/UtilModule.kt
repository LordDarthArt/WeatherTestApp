package tk.lorddarthart.utils.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import tk.lorddarthart.utils.helper.preferences.AppPreferences
import tk.lorddarthart.utils.helper.preferences.AppPreferencesImpl
import javax.inject.Singleton

@Module
class UtilModule {

    @Singleton
    @Provides
    fun provideGSON(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideAppPreferences(gson: Gson): AppPreferences = AppPreferencesImpl(gson)

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder
}