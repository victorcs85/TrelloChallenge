package br.com.victorcs.trellochallenge.di

import org.koin.core.module.Module
import org.koin.dsl.module

class TrelloInitialization: ModuleInitialization() {

    //region Data Module
    private val dataModule = module {
    }
    //endregion

    //region Domain Module
    private val domainModule = module {
    }
    //endregion

    //region Presentation Module
    private val presentationModule = module {
    }
    //endregion

    override fun init(): List<Module> = listOf(
        dataModule,
        domainModule,
        presentationModule
    )
}
