package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.Position.TOP
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboardsample.DataGenerator.createChanges
import net.furkanakdemir.noticeboardsample.DataGenerator.createWithUnreleasedChanges
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_ARRAY_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_ARRAY_XML
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.EMPTY_XML
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.INVALID_JSON
import net.furkanakdemir.noticeboardsample.MainActivity.InvalidSourceType.INVALID_XML
import net.furkanakdemir.noticeboardsample.MainActivity.Options.COLOR
import net.furkanakdemir.noticeboardsample.MainActivity.Options.TITLE
import net.furkanakdemir.noticeboardsample.MainActivity.Options.UNRELEASED
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

        builderSingle.setSingleChoiceItems(menuItems, INDEX_FIRST) { dialog, which ->
            currentDisplayOptions = when (which) {
                INDEX_FIRST -> DisplayOptions.ACTIVITY
                INDEX_SECOND -> DisplayOptions.DIALOG
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
            showIfOptions(it)
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

    private fun showIfOptions(it: SampleItem) {
        when (it.title) {
            UNRELEASED.type -> showUnreleased()
            COLOR.type -> showCustomColorProvider()
            TITLE.type -> showCustomTitle()
        }
    }

    private fun createSamples() {
        val samples = mutableListOf<SampleItem>()
        samples.add(Header(TITLE_OPTIONS))
        samples.addAll(Options.values().map { Sample(it.type) })
        samples.add(Header(TITLE_VALID_SAMPLES))
        samples.addAll(ValidSourceType.values().map { Sample(it.type) })
        samples.add(Header(TITLE_INVALID_SAMPLES))
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
        pinNoticeBoard(Dynamic(changes))
    }

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

    private fun showUnreleased() {
        NoticeBoard(this).pin {
            displayIn(currentDisplayOptions)
            source(Dynamic(createWithUnreleasedChanges()))
            unreleasedPosition(TOP)
        }
    }

    private fun showCustomColorProvider() {
        val customColorProvider = CustomColorProvider(this)
        val source = Dynamic(createChanges())
        pinNoticeBoard(source, customColorProvider)
    }

    private fun showCustomTitle() {
        val source = Dynamic(createChanges())
        pinNoticeBoard(source, title = TITLE_NOTICEBOARD)
    }

    private fun pinNoticeBoard(
        source: Source,
        colorProvider: ColorProvider? = null,
        title: String? = null
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
        }
    }

    companion object {
        private const val TITLE_OPTIONS = "Options"
        private const val TITLE_VALID_SAMPLES = "Valid Samples"
        private const val TITLE_INVALID_SAMPLES = "Invalid Samples"
        private const val TITLE_NOTICEBOARD_SAMPLE = "NoticeBoards"
        private const val TITLE_DISPLAY_OPTIONS_DIALOG = "Display Options"

        private const val TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE = "Close"

        private const val TITLE_NOTICEBOARD = "Change Logs"

        private const val INDEX_FIRST = 0
        private const val INDEX_SECOND = 1
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

    enum class Options(val type: String) {
        UNRELEASED("Unreleased Section"),
        COLOR("Custom Color"),
        TITLE("Custom Title"),
    }
}
