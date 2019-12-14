package net.furkanakdemir.noticeboardsample.invalid

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.invalid.InvalidActivity.Invalid.INVALID_CUSTOM_PLACEHOLDER
import net.furkanakdemir.noticeboardsample.invalid.InvalidActivity.Invalid.INVALID_JSON
import net.furkanakdemir.noticeboardsample.invalid.InvalidActivity.Invalid.INVALID_XML
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.util.ext.launch

class InvalidActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = Invalid.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_invalid

    override fun getOnClickListener(): (SampleItem) -> Unit = {
            when (it.title) {
                INVALID_JSON.type -> showInvalidJson()
                INVALID_XML.type -> showInvalidXml()
                INVALID_CUSTOM_PLACEHOLDER.type -> showCustomPlaceholder()
            }
        }

    private fun showInvalidXml() = launch<InvalidXmlSampleActivity>()

    private fun showInvalidJson() = launch<InvalidJsonSampleActivity>()

    private fun showCustomPlaceholder() = launch<InvalidCustomPlaceholderSampleActivity>()

    enum class Invalid(val type: String) {
        INVALID_JSON("Invalid format in Json File"),
        INVALID_XML("Invalid format in Xml File"),
        INVALID_CUSTOM_PLACEHOLDER("Invalid format with a custom placeholder")
    }
}
