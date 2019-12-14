package net.furkanakdemir.noticeboardsample.empty

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.empty.EmptyActivity.Empty.EMPTY_CUSTOM_PLACEHOLDER
import net.furkanakdemir.noticeboardsample.empty.EmptyActivity.Empty.EMPTY_DYNAMIC
import net.furkanakdemir.noticeboardsample.empty.EmptyActivity.Empty.EMPTY_JSON
import net.furkanakdemir.noticeboardsample.empty.EmptyActivity.Empty.EMPTY_XML
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.util.ext.launch

class EmptyActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = Empty.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_empty

    override fun getOnClickListener(): (SampleItem) -> Unit = {
        when (it.title) {
            EMPTY_DYNAMIC.type -> showEmptyDynamic()
            EMPTY_JSON.type -> showEmptyJson()
            EMPTY_XML.type -> showEmptyXml()
            EMPTY_CUSTOM_PLACEHOLDER.type -> showCustomPlaceholder()
        }
    }

    private fun showEmptyXml() = launch<EmptyXmlSampleActivity>()

    private fun showEmptyJson() = launch<EmptyJsonSampleActivity>()

    private fun showEmptyDynamic() = launch<EmptyDynamicSampleActivity>()

    private fun showCustomPlaceholder() = launch<EmptyCustomPlaceholderSampleActivity>()

    enum class Empty(val type: String) {
        EMPTY_DYNAMIC("Empty list dynamically"),
        EMPTY_JSON("Empty list in Json File"),
        EMPTY_XML("Empty list in Xml File"),
        EMPTY_CUSTOM_PLACEHOLDER("Custom Image and Text"),
    }
}
