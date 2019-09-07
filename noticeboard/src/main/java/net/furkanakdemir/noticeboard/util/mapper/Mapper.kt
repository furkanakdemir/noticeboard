package net.furkanakdemir.noticeboard.util.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}