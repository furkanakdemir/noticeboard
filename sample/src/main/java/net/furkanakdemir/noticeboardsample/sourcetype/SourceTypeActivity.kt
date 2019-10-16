package net.furkanakdemir.noticeboardsample.sourcetype

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.sourcetype.SourceTypeActivity.SourceType.DYNAMIC
import net.furkanakdemir.noticeboardsample.sourcetype.SourceTypeActivity.SourceType.JSON
import net.furkanakdemir.noticeboardsample.sourcetype.SourceTypeActivity.SourceType.XML
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.util.ext.launch

class SourceTypeActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = SourceType.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_source_type

    override fun getOnClickListener(): (SampleItem) -> Unit = {
            when (it.title) {
                DYNAMIC.type -> showDynamic()
                XML.type -> showValidXml()
                JSON.type -> showValidJson()
            }
        }

    private fun showDynamic() = launch<DynamicSampleActivity>()

    private fun showValidXml() = launch<XmlSampleActivity>()

    private fun showValidJson() = launch<JsonSampleActivity>()

    enum class SourceType(val type: String) {
        DYNAMIC("Dynamic"),
        XML("Xml"),
        JSON("Json")
    }
}
