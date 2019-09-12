package net.furkanakdemir.noticeboard.di

import dagger.Component
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import net.furkanakdemir.noticeboard.ui.NoticeBoardDialogFragment
import net.furkanakdemir.noticeboard.viewmodel.ViewModelModule

@NoticeBoardScope
@Component(modules = [NoticeBoardModule::class, ViewModelModule::class])
interface NoticeBoardComponent {
    fun inject(noticeBoardActivity: NoticeBoardActivity)
    fun inject(noticeBoard: NoticeBoard)
    fun inject(noticeBoardDialogFragment: NoticeBoardDialogFragment)
}