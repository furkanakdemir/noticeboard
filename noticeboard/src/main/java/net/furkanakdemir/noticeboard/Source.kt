package net.furkanakdemir.noticeboard

import net.furkanakdemir.noticeboard.data.model.Release

sealed class Source {
    class Dynamic(val items: List<Release> = emptyList()) : Source()
    class Xml(val filepath: String) : Source()
    class Json(val filepath: String) : Source()
}
