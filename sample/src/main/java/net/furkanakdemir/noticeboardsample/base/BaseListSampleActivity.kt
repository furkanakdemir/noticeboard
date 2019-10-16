package net.furkanakdemir.noticeboardsample.base

import android.os.Bundle
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.activity_base_list_sample.*
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.ui.SampleAdapter
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.util.CustomItemDecoration

abstract class BaseListSampleActivity : BaseToolbarActivity() {

    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    @StringRes
    abstract override fun getToolbarTitle(): Int

    abstract fun getOnClickListener(): (SampleItem) -> Unit

    abstract fun createSamples(): List<SampleItem>

    override fun layoutResId(): Int = R.layout.activity_base_list_sample

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
}
