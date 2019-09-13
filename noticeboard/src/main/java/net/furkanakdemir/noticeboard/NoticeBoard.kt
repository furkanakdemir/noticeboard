package net.furkanakdemir.noticeboard

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import net.furkanakdemir.noticeboard.config.ConfigRepository
import net.furkanakdemir.noticeboard.data.repository.NoticeBoardRepository
import net.furkanakdemir.noticeboard.di.DaggerInjector
import net.furkanakdemir.noticeboard.ui.NoticeBoardActivity
import net.furkanakdemir.noticeboard.ui.NoticeBoardDialogFragment
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import javax.inject.Inject

class NoticeBoard(private val target: FragmentActivity) {

    constructor(fragmentTarget: Fragment) :
            this(fragmentTarget.requireActivity())

    @VisibleForTesting
    var sourceType: Source = Source.Dynamic()
    @VisibleForTesting
    var displayOptions: DisplayOptions = DisplayOptions.ACTIVITY
    @VisibleForTesting
    var title: String = TITLE_DEFAULT

    private lateinit var observer: NoticeBoardLifeCycleObserver

    init {
        DaggerInjector.buildComponent(target)
        DaggerInjector.component?.inject(this)

        observeLifecycle()
    }

    @Inject
    internal lateinit var noticeBoardRepository: NoticeBoardRepository

    @Inject
    internal lateinit var configRepository: ConfigRepository

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
        configRepository.saveColorProvider(colorProvider)
    }

    private fun pin() {
        noticeBoardRepository.fetchChanges(sourceType)

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

    private fun observeLifecycle() {
        observer = NoticeBoardLifeCycleObserver(target)
    }

    internal inner class NoticeBoardLifeCycleObserver(lifecycleOwner: LifecycleOwner) :
        LifecycleObserver {
        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            DaggerInjector.clear()
        }
    }

    companion object {
        const val TITLE_DEFAULT = "NoticeBoard"
        const val KEY_TITLE = "KEY_TITLE"
    }
}
