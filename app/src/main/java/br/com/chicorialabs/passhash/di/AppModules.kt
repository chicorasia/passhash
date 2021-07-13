package br.com.chicorialabs.passhash.di

import br.com.chicorialabs.passhash.database.PasswordDatabase
import br.com.chicorialabs.passhash.repository.PasswordRepository
import br.com.chicorialabs.passhash.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { PasswordRepository(get()) }
}

val daoModule = module {
    single { PasswordDatabase.getInstance(androidContext()).passwordDao }
}
