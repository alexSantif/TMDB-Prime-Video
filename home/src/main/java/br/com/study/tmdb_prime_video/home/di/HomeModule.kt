package br.com.study.tmdb_prime_video.home.di

import br.com.study.tmdb_prime_video.home.data.network.HomeApi
import br.com.study.tmdb_prime_video.home.data.repository.HomeRepository
import br.com.study.tmdb_prime_video.home.data.repository.HomeRepositoryImpl
import br.com.study.tmdb_prime_video.home.domain.HomeUseCase
import br.com.study.tmdb_prime_video.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {

    val instance = module {

        viewModel {
            HomeViewModel(get())
        }

        factory {
            HomeUseCase(get())
        }

        factory<HomeRepository> {
            HomeRepositoryImpl(get())
        }

        fun homeServiceProvider(retrofit: Retrofit): HomeApi =
            retrofit.create(HomeApi::class.java)

        single { homeServiceProvider(get()) }
    }
}