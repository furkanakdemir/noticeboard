package net.furkanakdemir.noticeboard.data.model

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.ChangeType.UNRELEASED

open class Release(
    val date: String = "",
    val version: String = "",
    open val changes: List<Change> = emptyList(),
    internal val isReleased: Boolean = true
) {
    class Change(val description: String = "", val type: ChangeType = UNRELEASED) {
        override fun toString(): String {
            return "$description $type"
        }
    }

    override fun toString(): String {
        return "$date $version\n-------\n$changes\n"
    }
}
