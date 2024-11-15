package de.gero.domain

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

open class Website(uri: String) {


    private val uri = "https://$uri"
    private val client = OkHttpClient()

    protected fun call(adress: String): Response {
        val request = Request.Builder()
            .url(uri + adress)
            .build()

        return client.newCall(request).execute()
    }
}
