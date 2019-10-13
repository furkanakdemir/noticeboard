package net.furkanakdemir.noticeboard.data.model

class UnRelease(
    val title: String = TITLE_UNRELEASED_DEFAULT,
    override val changes: List<Change> = emptyList()
) : Release(date = title, changes = changes, isReleased = false) {

    override fun toString(): String {
        return "$title $version\n-------\n$changes\n"
    }

    companion object {
        internal const val TITLE_UNRELEASED_DEFAULT = "Unreleased"
    }
}
