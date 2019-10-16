package net.furkanakdemir.noticeboardsample.showrule

import net.furkanakdemir.noticeboard.ShowRule.Once
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class ShowOnceActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_show_rule_once

    override fun getToolbarTitle(): Int = R.string.title_show_rule_once

    override fun buttonAction() {
        val changes = DataGenerator.createChanges()
        pinNoticeBoard(Dynamic(changes), showRule = Once, tag = "ONCE")
    }
}
