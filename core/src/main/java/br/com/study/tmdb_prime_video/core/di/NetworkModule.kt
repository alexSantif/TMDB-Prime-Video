package br.com.study.tmdb_prime_video.core.di

import br.com.study.tmdb_prime_video.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
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
                .addInterceptor {
                    val request: Request =
                        it.request().newBuilder().addHeader("Authorization", BuildConfig.TMDB_BEARER).build()
                    it.proceed(request)
                }
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
        single { provideRetrofit(get(), get()) }
    }
}