package de.gero.domain

class WebElement(private val content: String) {

    override fun toString(): String {
        return this.content
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is WebElement) {
            return this.content == other.content
        }
        return false
    }

    fun scanForTag(tagString: String): List<WebElement> {
        var tagLevel = 0
        val result = mutableListOf<Tag>()
        // iterate over every character in the content
        for (i in tagString.length + 1..<content.length) {
            // mark beginning of tag
            if (content.substring(i - tagString.length - 1, i).contains("<$tagString")) {
                if (result.size == 0 || result[result.size - 1].endIndex != null) {
                    result.add(Tag(tagString, i + 1))
                } else {
                    result[result.size - 1].incrementNested()
                }
            }
            // mark end of tag
            else if (result.size > 0 && content.substring(i - tagString.length - 2, i + 1).contains("</$tagString>")) {
                try {
                    result[result.size - 1].setEnd(i - tagString.length - 2)
                } catch (e: IllegalArgumentException) {
                    throw IllegalArgumentException("Found here: ${this.content.substring(i-100, i+100)}", e)
                }
            }
        }

        return result.map { WebElement(content.substring(it.startIndex, it.endIndex!!)) }
    }

    class Tag(val tag: String, val startIndex: Int) {
        private var nestedTag: Int = 1
        var endIndex: Int? = null
            private set

        fun setEnd(endIndex: Int) {
            if (nestedTag == 1) {
                require(this.endIndex == null) { "endIndex of $tag starting at $startIndex can only be assigned once (${this.endIndex}, $endIndex)" }
                this.endIndex = endIndex
            } else {
                this.nestedTag--
            }
        }

        fun incrementNested() {
            this.nestedTag++
        }
    }

}
