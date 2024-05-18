package br.com.study.tmdb_prime_video.core.di

import br.com.study.tmdb_prime_video.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val REQUEST_HEADER = "Authorization"
    private const val TIMEOUT_TIME = 60L

    val instance = module {

        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .addInterceptor {
                    val request: Request =
                        it.request().newBuilder().addHeader(REQUEST_HEADER, BuildConfig.TMDB_BEARER).build()
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
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }

        single { provideHttpClient() }
        single { provideConverterFactory() }
        single { provideRetrofit(get(), get()) }
    }
}