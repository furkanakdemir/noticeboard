package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release


class MainActivity : SampleAdapter.OnSampleClickCallback, AppCompatActivity() {

    private lateinit var displayOptionsDialog: AlertDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var sampleAdapter: SampleAdapter

    private var currentDisplayOptions: DisplayOptions = DisplayOptions.ACTIVITY
    private var title: String = TITLE_NOTICEBOARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupRecyclerView()

        buildDisplayOptionsDialog()
    }

    private fun buildDisplayOptionsDialog() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle(TITLE_DISPLAY_OPTIONS_DIALOG)

        val menuItems = arrayOf(
            getString(R.string.action_display_activity),
            getString(R.string.action_display_dialog)
        )

        builderSingle.setSingleChoiceItems(menuItems, 0) { dialog, which ->
            currentDisplayOptions = when (which) {
                0 -> DisplayOptions.ACTIVITY
                1 -> DisplayOptions.DIALOG
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
        return when (item.itemId) {
            R.id.action_display_options -> {
                showDisplayOptionsDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDisplayOptionsDialog() {

        if (!displayOptionsDialog.isShowing) {
            displayOptionsDialog.show()
        }
    }

    private fun setupRecyclerView() {

        sampleAdapter = SampleAdapter(this)

        recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview).apply {
            setHasFixedSize(true)
            adapter = sampleAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        sampleAdapter.samples = SourceType.values().map {
            it.type
        }.toMutableList()
    }

    private fun setupToolbar() {
        setSupportActionBar(main_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.title = TITLE_NOTICEBOARD_SAMPLE
    }

    override fun onClicked(position: Int) {
        when (position) {
            SourceType.DYNAMIC.ordinal -> openDynamic()
            SourceType.XML.ordinal -> openXml()
            SourceType.JSON.ordinal -> openJson()
            SourceType.EMPTY_JSON.ordinal -> openEmptyFileJson()
            SourceType.EMPTY_ARRAY_JSON.ordinal -> openEmptyArrayJson()
            SourceType.INVALID_JSON.ordinal -> openInvalidJson()
            SourceType.EMPTY_XML.ordinal -> openEmptyFileXml()
            SourceType.EMPTY_ARRAY_XML.ordinal -> openEmptyArrayXml()
            SourceType.INVALID_XML.ordinal -> openInvalidXml()
        }
    }

    private fun openDynamic() {
        val newItems = mutableListOf(
            Release(
                "16 Sep 2019", "2.0.0",
                listOf(
                    Release.Change(
                        "New Login Page",
                        ChangeType.ADDED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.FIXED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.SECURITY.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.DEPRECATED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.REMOVED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.CHANGED.ordinal
                    )
                )
            ),
            Release(
                "16 Sep 2019", "2.0.0",
                listOf(
                    Release.Change(
                        "New Login Page",
                        ChangeType.ADDED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.FIXED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.SECURITY.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.DEPRECATED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.REMOVED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.CHANGED.ordinal
                    )
                )
            )
        )

        val myColorProvider = CustomColorProvider(this)

        NoticeBoard(this@MainActivity).pin {
            displayIn(currentDisplayOptions)
            title(title)
            source(Source.Dynamic(newItems))
            colorProvider(myColorProvider)
        }
    }

    private fun openInvalidXml() {
        val filepath = "sample_invalid.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }

    }

    private fun openEmptyArrayXml() {

        val filepath = "sample_empty.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openEmptyFileXml() {

        val filepath = "sample_empty_file.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openJson() {

        val filepath = "sample.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openEmptyArrayJson() {

        val filepath = "sample_empty.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openEmptyFileJson() {

        val filepath = "sample_empty_file.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openInvalidJson() {

        val filepath = "sample_invalid.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    private fun openXml() {
        val filepath = "sample.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
            title(title)
            displayIn(currentDisplayOptions)
        }
    }

    companion object {
        private const val TITLE_NOTICEBOARD_SAMPLE = "NoticeBoards"
        private const val TITLE_DISPLAY_OPTIONS_DIALOG = "Display Options"
        private const val TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE = "Close"

        private const val TITLE_NOTICEBOARD = "Change Logs"
    }

    enum class SourceType(val type: String) {
        DYNAMIC("Dynamic"),
        XML("Xml"),
        JSON("Json"),
        EMPTY_JSON("Empty Json"),
        EMPTY_ARRAY_JSON("Empty Array Json"),
        INVALID_JSON("Invalid Json"),
        EMPTY_XML("Empty Xml"),
        EMPTY_ARRAY_XML("Empty Array Xml"),
        INVALID_XML("Invalid Xml")
    }
}
