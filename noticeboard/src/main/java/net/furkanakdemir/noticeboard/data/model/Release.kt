package net.furkanakdemir.noticeboard.data.model

import net.furkanakdemir.noticeboard.ChangeType

data class Release(
    val date: String = "",
    val version: String = "",
    val changes: List<Change> = emptyList()
) {
    data class Change(
        val description: String = "",
        val type: Int = -1
    ) {
        constructor(description: String = "", type: ChangeType = ChangeType.ADDED) :
                this(description, type.ordinal)

        override fun toString(): String {
            return "$description $type"
        }
    }

    override fun toString(): String {
        return "$date $version\n-------\n$changes\n"
    }
}
