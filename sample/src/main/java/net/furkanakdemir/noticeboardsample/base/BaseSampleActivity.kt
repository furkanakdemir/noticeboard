package net.furkanakdemir.noticeboardsample.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

abstract class BaseSampleActivity : AppCompatActivity() {

    private lateinit var displayOptionsDialog: AlertDialog
    private var currentDisplayOptions: DisplayOptions = ACTIVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_sample)

        setupToolbar()
        buildDisplayOptionsDialog()

        descriptionTextView.text = getString(getDescription())
        pinButton.setOnClickListener { buttonAction() }
    }

    @StringRes
    abstract fun getDescription(): Int

    @StringRes
    abstract fun getToolbarTitle(): Int

    abstract fun buttonAction()

    private fun setupToolbar() {
        setSupportActionBar(baseSampleToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(getToolbarTitle())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun buildDisplayOptionsDialog() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle(R.string.dialog_display_options_close)

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

        builderSingle.setNegativeButton(TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE, null)
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
        title: String? = null,
        unreleasedPosition: Position = TOP,
        showRule: ShowRule = Always,
        tag: String? = null
    ) {
        NoticeBoard(this).pin {
            displayIn(currentDisplayOptions)
            source(source)
            colorProvider?.let {
                colorProvider(it)
            }
            title?.let {
                title(title)
            }

            tag?.let {
                tag(tag)
            }
            unreleasedPosition(unreleasedPosition)
            showRule(showRule)
        }
    }

    companion object {
        private const val TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE = "Close"

        private const val INDEX_FIRST = 0
        private const val INDEX_SECOND = 1
    }
}
