package net.furkanakdemir.noticeboard

import android.content.Context
import android.content.Intent
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.di.DaggerInjector
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import javax.inject.Inject

class NoticeBoard(
    val context: Context,
    private var sourceType: Source = Source.Dynamic(),
    private var displayOptions: DisplayOptions = DisplayOptions.ACTIVITY
) {
    init {
        DaggerInjector.buildComponent(context)
        DaggerInjector.component?.inject(this)
    }

    @Inject
    lateinit var noticeBoardRepository: NoticeBoardRepository

    fun pin(func: NoticeBoard.() -> Unit): NoticeBoard {
        this.func()
        this.pin()
        return this
    }

    fun source(source: Source) {
        this.sourceType = source
    }

    private fun pin() {
        noticeBoardRepository.fetchChanges(sourceType)

        when (displayOptions) {
            DisplayOptions.ACTIVITY -> context.startActivity(
                Intent(
                    context,
                    NoticeBoardActivity::class.java
                )
            )
            DisplayOptions.DIALOG -> TODO()
        }
    }
}