package net.furkanakdemir.noticeboardsample.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.R.string
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.R.id
import net.furkanakdemir.noticeboardsample.R.layout
import net.furkanakdemir.noticeboardsample.color.ColorSampleActivity
import net.furkanakdemir.noticeboardsample.empty.EmptyActivity
import net.furkanakdemir.noticeboardsample.invalid.InvalidActivity
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.COLOR
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.EMPTY
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.INVALID
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.SHOW_RULE
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.SOURCE_TYPE
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.TITLE
import net.furkanakdemir.noticeboardsample.main.MainActivity.Main.UNRELEASED
import net.furkanakdemir.noticeboardsample.showrule.ShowRuleSampleActivity
import net.furkanakdemir.noticeboardsample.sourcetype.SourceTypeActivity
import net.furkanakdemir.noticeboardsample.title.TitleSampleActivity
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Header
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Main
import net.furkanakdemir.noticeboardsample.unreleased.UnreleasedSampleActivity
import net.furkanakdemir.noticeboardsample.util.CustomItemDecoration
import net.furkanakdemir.noticeboardsample.util.ext.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupToolbar()
        setupRecyclerView()
        createMainMenu()
        clearPreferences()
    }

    private fun setupRecyclerView() {

        setupSampleAdapter()

        recyclerView = findViewById<RecyclerView>(id.main_recyclerview).apply {
            setHasFixedSize(true)
            adapter = sampleAdapter
            addItemDecoration(CustomItemDecoration(context))
        }
    }

    private fun setupSampleAdapter() {
        sampleAdapter = SampleAdapter {
            showMain(it)
        }
    }

    private fun showMain(it: SampleItem) {
        when (it.title) {
            SOURCE_TYPE.type -> showSourceType()
            EMPTY.type -> showEmpty()
            INVALID.type -> showInvalid()
            TITLE.type -> showTitle()
            UNRELEASED.type -> showUnreleased()
            COLOR.type -> showColor()
            SHOW_RULE.type -> showShowRule()
        }
    }

    private fun showSourceType() = launch<SourceTypeActivity>()

    private fun showEmpty() = launch<EmptyActivity>()

    private fun showInvalid() = launch<InvalidActivity>()

    private fun showTitle() = launch<TitleSampleActivity>()

    private fun showUnreleased() = launch<UnreleasedSampleActivity>()

    private fun showColor() = launch<ColorSampleActivity>()

    private fun showShowRule() = launch<ShowRuleSampleActivity>()

    private fun createMainMenu() {
        val samples = mutableListOf<SampleItem>()
        samples.add(Header(getString(R.string.title_samples)))
        samples.addAll(Main.values().map { Main(it.type) })

        sampleAdapter.samples = samples
    }

    private fun setupToolbar() {
        setSupportActionBar(mainToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.title = getString(R.string.title_main)
    }

    private fun clearPreferences() {
        val sharedPreferences =
            getSharedPreferences(getString(string.preference_file_key), Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    override fun onBackPressed() {
        // TODO Remove when memory leak is fixed
        if (isTaskRoot && supportFragmentManager.backStackEntryCount == 0) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    enum class Main(val type: String) {
        SOURCE_TYPE("Source Types"),
        EMPTY("Empty"),
        INVALID("Invalid"),
        TITLE("Custom Title"),
        UNRELEASED("Unreleased Section"),
        COLOR("Custom Color"),
        SHOW_RULE("Show Rules")
    }
}
