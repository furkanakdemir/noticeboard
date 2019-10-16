package net.furkanakdemir.noticeboardsample.showrule

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.showrule.ShowRuleSampleActivity.ShowRule.ALWAYS
import net.furkanakdemir.noticeboardsample.showrule.ShowRuleSampleActivity.ShowRule.LIMITED
import net.furkanakdemir.noticeboardsample.showrule.ShowRuleSampleActivity.ShowRule.ONCE
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.util.ext.launch

class ShowRuleSampleActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = ShowRule.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_show_rule

    override fun getOnClickListener(): (SampleItem) -> Unit = {
        when (it.title) {
            ONCE.type -> showOnce()
            LIMITED.type -> showLimited()
            ALWAYS.type -> showAlways()
        }
    }

    private fun showOnce() = launch<ShowOnceActivity>()

    private fun showLimited() = launch<ShowLimitedActivity>()

    private fun showAlways() = launch<ShowAlwaysActivity>()

    enum class ShowRule(val type: String) {
        ONCE("Only Once"),
        LIMITED("Limited"),
        ALWAYS("Always")
    }
}
