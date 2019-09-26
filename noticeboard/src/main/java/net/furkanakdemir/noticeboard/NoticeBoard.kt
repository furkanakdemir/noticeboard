package net.furkanakdemir.noticeboard

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import net.furkanakdemir.noticeboard.ui.NoticeBoardDialogFragment
import net.furkanakdemir.noticeboard.util.color.ColorProvider

class NoticeBoard(private val target: FragmentActivity) {

    constructor(fragmentTarget: Fragment) :
            this(fragmentTarget.requireActivity())

    @VisibleForTesting
    var sourceType: Source = Source.Dynamic()
    @VisibleForTesting
    var displayOptions: DisplayOptions = DisplayOptions.ACTIVITY
    @VisibleForTesting
    var title: String = TITLE_DEFAULT

    private val observer: NoticeBoardLifeCycleObserver

    private val dialogTag = NoticeBoardDialogFragment::class.java.canonicalName

    @VisibleForTesting
    internal var internalNoticeBoard: InternalNoticeBoard

    init {
        observer = NoticeBoardLifeCycleObserver(target)
        internalNoticeBoard = InternalNoticeBoard.getInstance(target)
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
        internalNoticeBoard.saveColorProvider(colorProvider)
    }

    fun pin(func: NoticeBoard.() -> Unit) {
        this.func()
        this.pin()
    }

    private fun pin() {
        internalNoticeBoard.fetchChanges(sourceType)

        when (displayOptions) {
            DisplayOptions.ACTIVITY -> target.startActivity(
                NoticeBoardActivity.createIntent(target, title)
            )
            DisplayOptions.DIALOG -> {
                val fragmentManager = target.supportFragmentManager
                val noticeBoardDialogFragment = NoticeBoardDialogFragment.newInstance(title)
                noticeBoardDialogFragment.show(fragmentManager, dialogTag)
            }
        }
    }

    inner class NoticeBoardLifeCycleObserver(lifecycleOwner: LifecycleOwner) : LifecycleObserver {
        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            InternalNoticeBoard.clear()
        }

    }

    companion object {
        const val TITLE_DEFAULT = "NoticeBoard"
        const val KEY_TITLE = "KEY_TITLE"
    }
}
