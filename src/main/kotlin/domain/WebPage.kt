package de.gero.domain

class WebPage(private val content: String) {

    fun scanForTag(tagString: String): List<String> {
        var tagLevel = 0
        val result = mutableListOf<Tag>()
        // iterate over every character in the content
        for (i in tagString.length + 1..<content.length) {
            val window = content.substring(i - tagString.length - 1, i)
            // mark beginning of tag
            if (window.contains("<$tagString")) {
                if (result.size == 0 || result.get(result.size - 1).endIndex != null) {
                    result.add(Tag(tagString, i + 1))
                } else {
                    result.get(result.size - 1).incrementNested()
                }
            }
            // mark end of tag
            else if (window.contains("/$tagString")) {
                result.get(result.size - 1).setEnd(i - tagString.length - 2)
            }
        }

        return result.map { content.substring(it.startIndex, it.endIndex!!) }
    }

    class Tag(val tag: String, val startIndex: Int) {
        private var nestedTag: Int = 1
        var endIndex: Int? = null
            private set

        fun setEnd(endIndex: Int) {
            if (nestedTag == 1) {
                require(this.endIndex == null) { "endIndex can only be assigned once" }
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
