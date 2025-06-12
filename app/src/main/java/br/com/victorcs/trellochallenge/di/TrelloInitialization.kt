package br.com.victorcs.trellochallenge.di

import br.com.victorcs.trellochallenge.core.providers.IDispatchersProvider
import br.com.victorcs.trellochallenge.core.providers.IDispatchersProviderImpl
import br.com.victorcs.trellochallenge.core.services.WifiService
import br.com.victorcs.trellochallenge.data.dto.BoardItemDto
import br.com.victorcs.trellochallenge.data.mapper.BoardItemMapper
import br.com.victorcs.trellochallenge.data.repository.BoardsRepositoryImpl
import br.com.victorcs.trellochallenge.data.source.remote.RetrofitConfig
import br.com.victorcs.trellochallenge.data.source.remote.TrelloService
import br.com.victorcs.trellochallenge.data.source.remote.inteceptor.ConnectivityInterceptor
import br.com.victorcs.trellochallenge.domain.mapper.DomainMapper
import br.com.victorcs.trellochallenge.domain.model.BoardItem
import br.com.victorcs.trellochallenge.domain.repository.IBoardsRepository
import br.com.victorcs.trellochallenge.presentation.features.boards.BoardsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private const val TRELLO_BASE_URL = "https://api.trello.com/1/members/me/"

class TrelloInitialization : ModuleInitialization() {

    //region Remote Module
    private val remoteModule = module {
        single { WifiService(context = androidContext()) }
        single<IDispatchersProvider> { IDispatchersProviderImpl() }
        single { ConnectivityInterceptor(wifiService = get()) }

        single {
            RetrofitConfig.create(
                service = TrelloService::class.java,
                baseUrl = TRELLO_BASE_URL,
                wifiService = get(),
            )
        }
    }
    //endregion

    //region Mapper Module
    private val mapperModule = module {
        single<DomainMapper<BoardItemDto, BoardItem>> { BoardItemMapper() }
    }
    // endregion

    //region Repository Module
    private val repositoryModule = module {
        single<IBoardsRepository> { BoardsRepositoryImpl(service = get(), mapper = get()) }
    }
    //endregion

    //region ViewModel Module
    private val viewModelModule = module {
        viewModel { BoardsViewModel(repository = get(), dispatchers = get()) }
    }
    //endregion

    override fun init(): List<Module> = listOf(
        remoteModule,
        mapperModule,
        repositoryModule,
        viewModelModule
    )
}
