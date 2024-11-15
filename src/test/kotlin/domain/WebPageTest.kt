package domain

import de.gero.domain.WebPage
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class WebPageTest {

    @Test
    fun scanForTag_singleTag() {
        // arrange
        val page = WebPage("Hier steht etwas <tag> expected </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(" expected ")

    }

    @Test
    fun scanForTag_twoTagsInARow() {
        // arrange
        val page = WebPage("Hier steht etwas <tag> expected </tag> <tag> more expected </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(" expected ", " more expected ")

    }

    @Test
    fun scanForTag_twoTagsNested() {
        // arrange
        val page = WebPage("Hier steht etwas <tag> expected <tag> more expected </tag> </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(" expected <tag> more expected </tag> ")

    }
}