package br.com.victorcs.trellochallenge.core.extensions

import br.com.victorcs.trellochallenge.core.GENERIC_MESSAGE_ERROR
import br.com.victorcs.trellochallenge.data.source.remote.exceptions.WithoutNetworkException
import br.com.victorcs.trellochallenge.domain.model.ErrorType
import br.com.victorcs.trellochallenge.domain.model.Response
import timber.log.Timber

fun Boolean?.orFalse() = this == true

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Response<T> {
    return try {
        Response.Success(apiCall.invoke())
    } catch (e: WithoutNetworkException) {
        Timber.e(e)
        Response.Error(e.message ?: GENERIC_MESSAGE_ERROR, ErrorType.NETWORK_ERROR)
    } catch (e: Exception) {
        Timber.e(e)
        Response.Error(GENERIC_MESSAGE_ERROR, ErrorType.GENERIC_ERROR)
    }
}
