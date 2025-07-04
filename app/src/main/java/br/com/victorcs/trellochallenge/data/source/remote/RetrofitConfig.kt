package br.com.victorcs.trellochallenge.data.source.remote


import br.com.victorcs.trellochallenge.BuildConfig
import br.com.victorcs.trellochallenge.core.services.WifiService
import br.com.victorcs.trellochallenge.data.source.remote.inteceptor.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    fun <T> create(service: Class<T>, baseUrl: String, wifiService: WifiService): T {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(wifiService))
            .addInterceptor(getHttpLogging())
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.create()))
            .build()
            .create(service)
    }

    private fun getHttpLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            },
        )
}
