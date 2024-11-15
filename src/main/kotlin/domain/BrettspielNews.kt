package de.gero.domain

import okhttp3.ResponseBody
import java.io.IOException

class BrettspielNews() : Website("brettspiel-news.de/"), INewsPage {

    fun nachrichtenUebersicht() {
        super.call("index.php/nachrichten-uebersicht").use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            println(response.body!!.string()) }
    }

    override fun news() {
        super.call("index.php/nachrichten-uebersicht").use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }

            println(response.body!!.contentInTag("ul"))
        }
    }
}

private fun ResponseBody.contentInTag(tag: String): List<String> {
//    val contents = listOf<String>()
    return this.string().split("<$tag").flatMap { s -> s.split("/$tag>") }.mapIndexed() { index, s -> "$index: \n$s\n" }
}
