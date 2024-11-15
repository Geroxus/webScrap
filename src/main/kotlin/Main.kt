package de.gero

import de.gero.domain.BrettspielNews
import de.gero.domain.HelloWorldSite
import de.gero.domain.INewsPage

fun main() {
    println("Hello World!")

    val site = HelloWorldSite()
//    site.helloWorld()

    val brettspielNews = BrettspielNews()
    brettspielNews.news()
//    brettspielNews.nachrichtenUebersicht()

    println(brettspielNews.javaClass.toString())
    println(brettspielNews.javaClass.superclass.toString())
    println(BrettspielNews::class.supertypes.toString())
    println(brettspielNews is INewsPage)
}