package net.furkanakdemir.noticeboardsample.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_base_sample.*
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.DisplayOptions.ACTIVITY
import net.furkanakdemir.noticeboard.DisplayOptions.DIALOG
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.Position
import net.furkanakdemir.noticeboard.Position.TOP
import net.furkanakdemir.noticeboard.ShowRule
import net.furkanakdemir.noticeboard.ShowRule.Always
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboardsample.R

@Suppress("LongParameterList")
abstract class BaseSampleActivity : BaseToolbarActivity() {

    private lateinit var displayOptionsDialog: AlertDialog
    private var currentDisplayOptions: DisplayOptions = ACTIVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buildDisplayOptionsDialog()

        descriptionTextView.text = getString(getDescription())
        pinButton.setOnClickListener { buttonAction() }
    }

    @StringRes
    abstract fun getDescription(): Int

    @StringRes
    abstract override fun getToolbarTitle(): Int

    abstract fun buttonAction()

    @LayoutRes
    override fun layoutResId(): Int = R.layout.activity_base_sample

    private fun buildDisplayOptionsDialog() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle(R.string.action_display_options)

        val menuItems = arrayOf(
            getString(R.string.action_display_activity),
            getString(R.string.action_display_dialog)
        )

        builderSingle.setSingleChoiceItems(
            menuItems,
            INDEX_FIRST
        ) { dialog, which ->
            currentDisplayOptions = when (which) {
                INDEX_FIRST -> ACTIVITY
                INDEX_SECOND -> DIALOG
                else -> throw IllegalArgumentException("Invalid index $which")
            }

            dialog.dismiss()
        }

        builderSingle.setNegativeButton(getString(R.string.dialog_display_options_close), null)
        displayOptionsDialog = builderSingle.create()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sample, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (item.itemId == R.id.action_display_options) {
            showDisplayOptionsDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showDisplayOptionsDialog() {

        if (!displayOptionsDialog.isShowing) {
            displayOptionsDialog.show()
        }
    }

    protected fun pinNoticeBoard(
        source: Source,
        colorProvider: ColorProvider? = null,
        title: String = "",
        unreleasedPosition: Position = TOP,
        showRule: ShowRule = Always,
        tag: String = "",
        emptyText: String = "",
        @DrawableRes emptyIcon: Int = -1,
        errorText: String = "",
        @DrawableRes errorIcon: Int = -1
    ) {
        NoticeBoard(this).pin {
            displayIn(currentDisplayOptions)
            source(source)
            colorProvider?.let { colorProvider(it) }
            title(title)
            tag(tag)
            unreleasedPosition(unreleasedPosition)
            showRule(showRule)
            setEmpty(emptyText, emptyIcon)
            setError(errorText, errorIcon)
        }
    }

    companion object {
        private const val INDEX_FIRST = 0
        private const val INDEX_SECOND = 1
    }
}
