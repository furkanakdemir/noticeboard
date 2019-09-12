package net.furkanakdemir.noticeboard.di

import android.content.Context

class DaggerInjector private constructor() {
    companion object {

        var component: NoticeBoardComponent? = null
            private set

        fun buildComponent(context: Context): NoticeBoardComponent? {
            component = DaggerNoticeBoardComponent
                .builder()
                .noticeBoardModule(NoticeBoardModule(context))
                .build()
            return component
        }

        fun clear() {
            component = null
        }
    }
}