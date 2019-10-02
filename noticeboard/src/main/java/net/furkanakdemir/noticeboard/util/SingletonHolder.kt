package net.furkanakdemir.noticeboard.util

open class SingletonHolder<out T : Any, in A>(private val creator: (A?) -> T) {
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A? = null): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator(arg)
                instance = created
                created
            }
        }
    }

    fun clear() {
        instance = null
    }
}
