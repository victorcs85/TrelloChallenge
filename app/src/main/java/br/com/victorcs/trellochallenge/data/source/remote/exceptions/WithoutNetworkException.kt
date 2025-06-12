package br.com.victorcs.trellochallenge.data.source.remote.exceptions

import br.com.victorcs.trellochallenge.core.NETWORK_ERROR
import java.io.IOException

class WithoutNetworkException : IOException(NETWORK_ERROR)
