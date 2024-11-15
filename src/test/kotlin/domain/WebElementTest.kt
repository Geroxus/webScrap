package domain

import de.gero.domain.WebElement
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class WebElementTest {

    @Test
    fun scanForTag_singleTag() {
        // arrange
        val page = WebElement("Hier steht etwas <tag> expected </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(WebElement(" expected "))

    }

    @Test
    fun scanForTag_twoTagsInARow() {
        // arrange
        val page = WebElement("Hier steht etwas <tag> expected </tag> <tag> more expected </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(WebElement(" expected "), WebElement(" more expected "))

    }

    @Test
    fun scanForTag_twoTagsNested() {
        // arrange
        val page = WebElement("Hier steht etwas <tag> expected <tag> more expected </tag> </tag> ende")

        // act
        val result = page.scanForTag("tag")

        // assert
        assertThat(result).contains(WebElement(" expected <tag> more expected </tag> "))

    }
}