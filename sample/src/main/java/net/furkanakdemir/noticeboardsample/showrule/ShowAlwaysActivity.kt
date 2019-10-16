package net.furkanakdemir.noticeboardsample.showrule

import net.furkanakdemir.noticeboard.ShowRule.Always
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class ShowAlwaysActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml

    override fun getToolbarTitle(): Int = R.string.title_xml

    override fun buttonAction() {
        val changes = DataGenerator.createChanges()
        pinNoticeBoard(Dynamic(changes), showRule = Always, tag = "ALWAYS")
    }
}
