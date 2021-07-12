package br.com.chicorialabs.passhash.di

import br.com.chicorialabs.passhash.repository.PasswordRepository
import br.com.chicorialabs.passhash.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { PasswordRepository() }
}

// TODO 007: Adicionar o daoModule