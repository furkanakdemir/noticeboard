package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider


class MainActivity : SampleAdapter.OnSampleClickCallback, AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupRecyclerView()
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

        Handler().postDelayed(
            {
                recyclerView.findViewHolderForLayoutPosition(0)?.itemView?.performClick()
            }, 500
        )

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

        val myColorProvider = NoticeBoardColorProvider(this)
        myColorProvider.COLOR_ADDED = ContextCompat.getColor(this, R.color.colorAccent)

        NoticeBoard(this@MainActivity).pin {
            displayIn(DisplayOptions.DIALOG)
            title("ChangeLogs")
            source(Source.Dynamic(newItems))
        }
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

    private fun openInvalidXml() {
        val filepath = "sample_invalid.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
            title("Recent Changes")
        }

    }

    private fun openEmptyArrayXml() {

        val filepath = "sample_empty.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
        }
    }

    private fun openEmptyFileXml() {

        val filepath = "sample_empty_file.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
        }
    }

    private fun openJson() {

        val filepath = "sample.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
        }
    }

    private fun openEmptyArrayJson() {

        val filepath = "sample_empty.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
        }
    }

    private fun openEmptyFileJson() {

        val filepath = "sample_empty_file.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
        }
    }

    private fun openInvalidJson() {

        val filepath = "sample_invalid.json"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Json(filepath))
        }
    }

    private fun openXml() {
        val filepath = "sample.xml"

        NoticeBoard(this@MainActivity).pin {
            source(Source.Xml(filepath))
        }
    }

    companion object {
        private const val TITLE_NOTICEBOARD_SAMPLE = "NoticeBoards"
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
