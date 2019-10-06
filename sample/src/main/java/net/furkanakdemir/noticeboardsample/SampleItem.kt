package net.furkanakdemir.noticeboardsample

sealed class SampleItem(val title: String) {
    data class Header(val text: String) : SampleItem(text)
    data class Sample(val text: String) : SampleItem(text)
}
