package br.com.study.tmdb_prime_video.core.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    val instance = module {

        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
        }


        fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()


        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }

        single { provideHttpClient() }
        single { provideConverterFactory() }
        single { provideRetrofit(get(),get()) }
    }
}