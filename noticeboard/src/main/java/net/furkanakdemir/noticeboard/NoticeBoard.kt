package net.furkanakdemir.noticeboard

import android.content.Context
import android.content.Intent
import net.furkanakdemir.noticeboard.data.InMemoryNoticeBoardRepository
import net.furkanakdemir.noticeboard.data.NoticeBoardRepository

class NoticeBoard(
    private val context: Context,
    private var sourceType: Source = Source.Dynamic(),
    private var displayOptions: DisplayOptions = DisplayOptions.ACTIVITY
) {

    private val noticeBoardRepository: NoticeBoardRepository = InMemoryNoticeBoardRepository

    inline fun pin(func: NoticeBoard.() -> Unit): NoticeBoard {
        this.func()
        this.pin()
        return this
    }

    fun data(items: List<NoticeBoardItem>) {
        noticeBoardRepository.saveChanges(items)
    }

    fun source(source: Source) {
        this.sourceType = source

        when (source) {
            is Source.Dynamic -> noticeBoardRepository.saveChanges(source.items)
            is Source.Xml -> TODO()
            is Source.Json -> TODO()
        }
    }

    fun pin() {
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