package br.com.victorcs.trellochallenge.data.source.remote.inteceptor

import br.com.victorcs.trellochallenge.core.NETWORK_ERROR
import br.com.victorcs.trellochallenge.core.services.WifiService
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val wifiService: WifiService) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (wifiService.isOnline().not()) {
            throw IOException(NETWORK_ERROR)
        } else {
            return chain.proceed(chain.request())
        }
    }
}