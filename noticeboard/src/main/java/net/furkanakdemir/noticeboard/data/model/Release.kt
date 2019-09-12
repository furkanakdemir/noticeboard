package net.furkanakdemir.noticeboard.data.model

data class Release(
    val date: String = "",
    val version: String = "",
    val changes: List<Change> = emptyList()
) {
    data class Change(
        val description: String = "",
        val type: Int = -1
    ) {
        override fun toString(): String {
            return "$description $type"
        }
    }

    override fun toString(): String {
        return "$date $version\n-------\n$changes\n"
    }
}
