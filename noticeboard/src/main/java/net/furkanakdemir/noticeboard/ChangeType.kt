package net.furkanakdemir.noticeboard

enum class ChangeType(val type: Int) {
    UNRELEASED(-1),
    ADDED(0),
    CHANGED(1),
    DEPRECATED(2),
    REMOVED(3),
    FIXED(4),
    SECURITY(5);

    companion object {
        fun of(type: Int) = values().firstOrNull { it.type == type } ?: UNRELEASED
    }

}
