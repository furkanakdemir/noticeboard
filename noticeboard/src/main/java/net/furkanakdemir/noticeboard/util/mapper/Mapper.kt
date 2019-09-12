package net.furkanakdemir.noticeboard.util.mapper

internal interface Mapper<I, O> {
    fun map(input: I): O
}
