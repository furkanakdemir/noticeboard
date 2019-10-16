package net.furkanakdemir.noticeboardsample.showrule

import net.furkanakdemir.noticeboard.ShowRule.Limited
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class ShowLimitedActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml

    override fun getToolbarTitle(): Int = R.string.title_xml

    override fun buttonAction() {
        val changes = DataGenerator.createChanges()
        pinNoticeBoard(Dynamic(changes), showRule = Limited(NUMBER_OF_SHOW), tag = "LIMITED")
    }

    companion object {
        private const val NUMBER_OF_SHOW = 5
    }
}
