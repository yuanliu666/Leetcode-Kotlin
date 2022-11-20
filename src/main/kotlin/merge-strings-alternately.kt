class solution_merge_strings_alternatively {

    // T:O(m+n) S:O(m+n)
    fun mergeAlternately(word1: String, word2: String): String {
        val sb = StringBuilder(word1.length + word2.length)
        var i = 0
        var j = 0
        while (i < word1.length && j < word2.length) {
            sb.append(word1[i++])
            sb.append(word2[j++])
        }
        while (i < word1.length) {
            sb.append(word1[i++])
        }
        while (j < word2.length) {
            sb.append(word2[j++])
        }
        return sb.toString()
    }
}