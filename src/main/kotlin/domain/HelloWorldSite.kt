package de.gero.domain

import java.io.IOException

class HelloWorldSite : Website("publicobject.com/") {
    fun helloWorld() {
        super.call("helloworld.txt").use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            println(response.body!!.string()) }
    }
}