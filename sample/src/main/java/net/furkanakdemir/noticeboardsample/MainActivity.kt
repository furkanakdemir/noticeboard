package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_ARRAY_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_ARRAY_XML
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_XML
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.INVALID_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.INVALID_XML
import net.furkanakdemir.noticeboardsample.MainActivity.ValidSourceType.DYNAMIC
import net.furkanakdemir.noticeboardsample.MainActivity.ValidSourceType.JSON
import net.furkanakdemir.noticeboardsample.MainActivity.ValidSourceType.XML
import net.furkanakdemir.noticeboardsample.SampleItem.Header
import net.furkanakdemir.noticeboardsample.SampleItem.Sample

@Suppress("TooManyFunctions")
class MainActivity : AppCompatActivity() {

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

        createSamples()

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

        setupSampleAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview).apply {
            setHasFixedSize(true)
            adapter = sampleAdapter
            addItemDecoration(CustomItemDecoration(context))
        }
    }

    private fun setupSampleAdapter() {
        sampleAdapter = SampleAdapter {
            showIfValid(it)
            showIfInvalid(it)
        }
    }

    private fun showIfInvalid(it: SampleItem) {
        when (it.title) {
            EMPTY_JSON.type -> showEmptyFileJson()
            EMPTY_ARRAY_JSON.type -> showEmptyArrayJson()
            INVALID_JSON.type -> showInvalidJson()
            EMPTY_XML.type -> showEmptyFileXml()
            EMPTY_ARRAY_XML.type -> showEmptyArrayXml()
            INVALID_XML.type -> showInvalidXml()
        }
    }

    private fun showIfValid(it: SampleItem) {
        when (it.title) {
            DYNAMIC.type -> showDynamic()
            XML.type -> showValidXml()
            JSON.type -> showValidJson()
        }
    }

    private fun createSamples() {
        val samples = mutableListOf<SampleItem>()
        samples.add(Header("Valid Samples"))
        samples.addAll(ValidSourceType.values().map { Sample(it.type) })
        samples.add(Header("Invalid Samples"))
        samples.addAll(InvalidSourceType.values().map { Sample(it.type) })

        sampleAdapter.samples = samples
    }

    private fun setupToolbar() {
        setSupportActionBar(main_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.title = TITLE_NOTICEBOARD_SAMPLE
    }

    private fun showDynamic() {
        val changes = createChanges()
        val customColorProvider = CustomColorProvider(this)

        pinNoticeBoard(Dynamic(changes), customColorProvider)
    }

    private fun createChanges(): List<Release> = listOf(
        Release(
            "30 Sep 2019", "1.0.0",
            listOf(
                Change("New Login Page", ADDED),
                Change("Crash in Payment", CHANGED),
                Change("Old theme will be removed", DEPRECATED),
                Change("Tutorial page is removed", REMOVED),
                Change("Crash in Payment", FIXED),
                Change("HTTPS only requests", SECURITY)
            )
        )
    )

    private fun showValidXml() = showXmlWithFilepath("sample.xml")

    private fun showInvalidXml() = showXmlWithFilepath("sample_invalid.xml")

    private fun showEmptyArrayXml() = showXmlWithFilepath("sample_empty.xml")

    private fun showEmptyFileXml() = showXmlWithFilepath("sample_empty_file.xml")

    private fun showValidJson() = showJsonWithFilepath("sample.json")

    private fun showEmptyArrayJson() = showJsonWithFilepath("sample_empty.json")

    private fun showEmptyFileJson() = showJsonWithFilepath("sample_empty_file.json")

    private fun showInvalidJson() = showJsonWithFilepath("sample_invalid.json")

    private fun showXmlWithFilepath(filepath: String) = pinNoticeBoard(Source.Xml(filepath))

    private fun showJsonWithFilepath(filepath: String) = pinNoticeBoard(Source.Json(filepath))


    private fun pinNoticeBoard(source: Source, colorProvider: ColorProvider? = null) {
        NoticeBoard(this).pin {
            displayIn(currentDisplayOptions)
            title(title)
            source(source)
            colorProvider?.let {
                colorProvider(it)
            }
        }
    }

    companion object {
        private const val TITLE_NOTICEBOARD_SAMPLE = "NoticeBoards"
        private const val TITLE_DISPLAY_OPTIONS_DIALOG = "Display Options"
        private const val TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE = "Close"

        private const val TITLE_NOTICEBOARD = "Change Logs"
    }

    override fun onBackPressed() {
        // TODO Remove when memory leak is fixed
        if (isTaskRoot && supportFragmentManager.backStackEntryCount == 0) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    enum class ValidSourceType(val type: String) {
        DYNAMIC("Dynamic"),
        XML("Xml"),
        JSON("Json")
    }

    enum class InvalidSourceType(val type: String) {
        EMPTY_JSON("Empty Json File"),
        EMPTY_ARRAY_JSON("Empty Array in Json File"),
        INVALID_JSON("Invalid format in Json File"),
        EMPTY_XML("Empty Xml File"),
        EMPTY_ARRAY_XML("Empty Array in Xml File"),
        INVALID_XML("Invalid format in Xml File")
    }
}
