package ua.youdin.a2020giphyrestapi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ua.youdin.a2020giphyrestapi.data.Repository
import ua.youdin.a2020giphyrestapi.data.giphyAPI.GiphyService
import ua.youdin.a2020giphyrestapi.data.localDB.RepoDatabase
import ua.youdin.a2020giphyrestapi.ui.searsh.SharedSearchRepositoriesViewModel


@KoinApiExtension
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        addTheme()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }

    private fun addTheme() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }

    val appModule = module {
        single(
            named("DataBase"),
            createdAtStart = true
        ) { RepoDatabase.getInstance(androidContext()) }

        single(
            named("GiphyService"),
            createdAtStart = true
        ) {
            GiphyService.create()
        }
        single(
            named("Repository"),
            createdAtStart = true
        ) {
            Repository(
                get(qualifier = named("GiphyService")),
                get(qualifier = named("DataBase"))
            )
        }

        viewModel { SharedSearchRepositoriesViewModel(get(qualifier = named("Repository"))) }
    }

}
