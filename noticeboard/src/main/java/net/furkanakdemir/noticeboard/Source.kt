package net.furkanakdemir.noticeboard

sealed class Source {
    class Dynamic(val items: List<NoticeBoardItem> = emptyList()) : Source()
    class Xml(val filepath: String) : Source()
    class Json(val filepath: String) : Source()
}
