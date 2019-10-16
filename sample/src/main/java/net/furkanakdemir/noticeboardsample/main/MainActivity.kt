package net.furkanakdemir.noticeboardsample.main

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.R.string
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.R.id
import net.furkanakdemir.noticeboardsample.base.BaseToolbarActivity
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

class MainActivity : BaseToolbarActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sampleAdapter: SampleAdapter

    override fun layoutResId(): Int = R.layout.activity_main

    override fun getToolbarTitle(): Int = R.string.title_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(false)
        setupRecyclerView()
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
            when (it.title) {
                SOURCE_TYPE.type -> launch<SourceTypeActivity>()
                EMPTY.type -> launch<EmptyActivity>()
                INVALID.type -> launch<InvalidActivity>()
                TITLE.type -> launch<TitleSampleActivity>()
                UNRELEASED.type -> launch<UnreleasedSampleActivity>()
                COLOR.type -> launch<ColorSampleActivity>()
                SHOW_RULE.type -> launch<ShowRuleSampleActivity>()
            }
        }

        val samples = mutableListOf<SampleItem>()
        samples.add(Header(getString(R.string.title_samples)))
        samples.addAll(Main.values().map { Main(it.type) })

        sampleAdapter.samples = samples
    }

    private fun clearPreferences() {
        val sharedPreferences =
            getSharedPreferences(getString(string.preference_file_key), MODE_PRIVATE)
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
