package net.furkanakdemir.noticeboard

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import net.furkanakdemir.noticeboard.ui.NoticeBoardDialogFragment
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboard.util.io.DefaultFileReader

class NoticeBoard(private val target: FragmentActivity) {

    constructor(fragmentTarget: Fragment) :
            this(fragmentTarget.requireActivity())

    @VisibleForTesting
    var sourceType: Source = Source.Dynamic()
    @VisibleForTesting
    var displayOptions: DisplayOptions = DisplayOptions.ACTIVITY
    @VisibleForTesting
    var title: String = TITLE_DEFAULT

    init {
        InternalNoticeBoard.defaultColorProvider = NoticeBoardColorProvider(target)
        InternalNoticeBoard.fileReader = DefaultFileReader(target)
        InternalNoticeBoard.setup()
    }

    fun pin(func: NoticeBoard.() -> Unit): NoticeBoard {
        this.func()
        this.pin()
        return this
    }

    fun source(source: Source) {
        this.sourceType = source
    }

    fun displayIn(displayOptions: DisplayOptions) {
        this.displayOptions = displayOptions
    }

    fun title(text: String) {
        title = if (text.isEmpty()) {
            TITLE_DEFAULT
        } else {
            text
        }
    }

    fun colorProvider(colorProvider: ColorProvider) {
        InternalNoticeBoard.saveColorProvider(colorProvider)
    }

    private fun pin() {


        InternalNoticeBoard.fetchChanges(sourceType)

        when (displayOptions) {
            DisplayOptions.ACTIVITY -> target.startActivity(
                NoticeBoardActivity.createIntent(target, title)
            )
            DisplayOptions.DIALOG -> {
                val fm = target.supportFragmentManager
                val noticeBoardDialogFragment = NoticeBoardDialogFragment.newInstance(title)
                noticeBoardDialogFragment.show(
                    fm,
                    NoticeBoardDialogFragment::class.java.canonicalName
                )
            }
        }
    }

    companion object {
        const val TITLE_DEFAULT = "NoticeBoard"
        const val KEY_TITLE = "KEY_TITLE"
    }
}
