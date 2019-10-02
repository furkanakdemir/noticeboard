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
    lateinit var sourceType: Source
    @VisibleForTesting
    lateinit var displayOptions: DisplayOptions
    @VisibleForTesting
    lateinit var title: String

    private val observer: NoticeBoardLifeCycleObserver

    private val dialogTag = NoticeBoardDialogFragment::class.java.canonicalName

    @VisibleForTesting
    internal var internalNoticeBoard: InternalNoticeBoard

    init {
        observer = NoticeBoardLifeCycleObserver(target)
        internalNoticeBoard = InternalNoticeBoard.getInstance(target)
        initialize()
    }

    private fun initialize() {
        sourceType = Source.Dynamic()
        displayOptions = DisplayOptions.ACTIVITY
        title = TITLE_DEFAULT
        internalNoticeBoard.setDefaultColorProvider()
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
        initialize()
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

    private class NoticeBoardLifeCycleObserver(lifecycleOwner: LifecycleOwner) : LifecycleObserver {
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
