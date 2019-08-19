package net.furkanakdemir.noticeboard

import android.content.Context
import android.content.Intent

class NoticeBoard private constructor(private val context: Context, val source: Source) {


    private constructor(builder: Builder) : this(builder.context, builder.source)

    companion object {
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }


    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        internal lateinit var context: Context
        internal var source: Source = Source.DYNAMIC

        fun context(context: Context) {
            this.context = context
        }

        fun source(source: Source) {
            this.source = source
        }

        fun build() = NoticeBoard(this)
    }

    fun pin() {
        context.startActivity(Intent(context, NoticeBoardActivity::class.java))
    }


    enum class Source {
        DYNAMIC,
        XML,
        JSON
    }

}