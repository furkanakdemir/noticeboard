package net.furkanakdemir.noticeboard

sealed class ShowRule(open val number: Int) {

    class Once : ShowRule(1)
    class Always : ShowRule(Int.MAX_VALUE)
    class Limit(override val number: Int) : ShowRule(number)
}
