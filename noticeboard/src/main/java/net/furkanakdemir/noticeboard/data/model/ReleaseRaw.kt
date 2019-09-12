package net.furkanakdemir.noticeboard.data.model

data class ReleaseRaw(
    val changes: List<ChangeRaw> = emptyList(),
    val date: String = "",
    val version: String = ""
) {
    data class ChangeRaw(
        val description: String = "",
        val type: Int = -1
    )
}
