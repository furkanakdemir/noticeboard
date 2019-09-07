package net.furkanakdemir.noticeboard.di

import dagger.Component
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NoticeBoardModule::class])
interface NoticeBoardComponent {
    fun inject(noticeBoardActivity: NoticeBoardActivity)
    fun inject(noticeBoard: NoticeBoard)
}