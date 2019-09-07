package net.furkanakdemir.noticeboard.data.model


import com.google.gson.annotations.SerializedName

data class ReleaseRaw(
    @SerializedName("changes") val changes: List<ChangeRaw> = emptyList(),
    @SerializedName("date") val date: String = "",
    @SerializedName("version") val version: String = ""
) {
    data class ChangeRaw(
        @SerializedName("description") val description: String = "",
        @SerializedName("type") val type: Int = -1
    )
}