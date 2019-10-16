package net.furkanakdemir.noticeboardsample.unreleased

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.unreleased.UnreleasedSampleActivity.Unreleased.BOTTOM
import net.furkanakdemir.noticeboardsample.unreleased.UnreleasedSampleActivity.Unreleased.NONE
import net.furkanakdemir.noticeboardsample.unreleased.UnreleasedSampleActivity.Unreleased.TOP
import net.furkanakdemir.noticeboardsample.util.ext.launch


class UnreleasedSampleActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = Unreleased.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_source_type

    override fun getOnClickListener(): (SampleItem) -> Unit =
        {
            when (it.title) {
                TOP.type -> showTop()
                BOTTOM.type -> showBottom()
                NONE.type -> showNone()
            }
        }


    private fun showTop() = launch<UnreleasedTopActivity>()
    private fun showBottom() = launch<UnreleasedBottomActivity>()
    private fun showNone() = launch<UnreleasedNoneActivity>()


    enum class Unreleased(val type: String) {
        TOP("Top"),
        BOTTOM("Bottom"),
        NONE("None")
    }
}
