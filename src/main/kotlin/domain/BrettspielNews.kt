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

            val page = response.body!!.toWebElement()

            println(page)
            println(page.scanForTag("ul")[1].scanForTag("li"))
        }
    }
}

private fun ResponseBody.toWebElement(): WebElement {
    return WebElement(this.string())
}
