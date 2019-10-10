package net.furkanakdemir.noticeboard

enum class Position {
    NONE,
    START,
    END,
    TOP,
    BOTTOM;

    fun isTop(): Boolean = this == TOP

    fun isNone(): Boolean = this == NONE

    fun isBottom(): Boolean = this == BOTTOM

    fun isStart(): Boolean = this == START

    fun isEnd(): Boolean = this == END
}
