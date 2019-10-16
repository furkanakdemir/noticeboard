package net.furkanakdemir.noticeboardsample.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base_list_sample.*
import net.furkanakdemir.noticeboardsample.R.layout
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.util.CustomItemDecoration

abstract class BaseListSampleActivity : AppCompatActivity() {

    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_base_list_sample)

        setupToolbar()
        setupRecyclerView()
    }

    @StringRes
    abstract fun getToolbarTitle(): Int

    abstract fun getOnClickListener(): (SampleItem) -> Unit

    abstract fun createSamples(): List<SampleItem>

    private fun setupToolbar() {
        setSupportActionBar(baseListSampleToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(getToolbarTitle())
    }

    private fun setupRecyclerView() {
        setupSampleAdapter()

        baseListRecyclerview.apply {
            setHasFixedSize(true)
            adapter = sampleAdapter
            addItemDecoration(CustomItemDecoration(context))
        }
    }

    private fun setupSampleAdapter() {

        val samples = createSamples().toMutableList()

        sampleAdapter = SampleAdapter(getOnClickListener())
        sampleAdapter.samples = samples
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val TITLE_DISPLAY_OPTIONS_DIALOG = "Display Options"

        private const val TEXT_DISPLAY_OPTIONS_DIALOG_CLOSE = "Close"

        private const val TITLE_NOTICEBOARD = "Change Logs"

        private const val INDEX_FIRST = 0
        private const val INDEX_SECOND = 1
    }
}
