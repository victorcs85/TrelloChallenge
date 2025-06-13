package br.com.victorcs.trellochallenge.core.extensions

import br.com.victorcs.trellochallenge.core.constants.GENERIC_MESSAGE_ERROR
import br.com.victorcs.trellochallenge.core.constants.NETWORK_ERROR
import br.com.victorcs.trellochallenge.data.source.remote.exceptions.WithoutNetworkException
import br.com.victorcs.trellochallenge.domain.model.ErrorType
import br.com.victorcs.trellochallenge.domain.model.Response
import timber.log.Timber
import java.io.IOException

fun Boolean?.orFalse() = this == true

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Response<T> {
    return try {
        Response.Success(apiCall())
    } catch (e: Exception) {
        Timber.e(e)
        when (e) {
            is WithoutNetworkException, is IOException ->
                Response.Error(e.message ?: NETWORK_ERROR, ErrorType.NETWORK_ERROR)
            else ->
                Response.Error(GENERIC_MESSAGE_ERROR, ErrorType.GENERIC_ERROR)
        }
    }
}
