package br.com.victorcs.trellochallenge.data.source.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiBuilder {
    fun create(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
