package net.furkanakdemir.noticeboard

sealed class ShowRule(open val number: Int) {

    object Once : ShowRule(1)
    object Always : ShowRule(Int.MAX_VALUE)
    class Limited(override val number: Int) : ShowRule(number)
}
