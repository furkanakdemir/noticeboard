package net.furkanakdemir.noticeboard.data.model

internal data class ReleaseRaw(
    val changes: List<ChangeRaw> = emptyList(),
    val date: String = "",
    val version: String = "",
    val released: Boolean = true
) {
    data class ChangeRaw(
        val description: String = "",
        val type: Int = -1
    )
}
