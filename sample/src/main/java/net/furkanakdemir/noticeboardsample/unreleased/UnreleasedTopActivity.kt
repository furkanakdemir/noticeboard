package net.furkanakdemir.noticeboardsample.unreleased

import net.furkanakdemir.noticeboard.Position.TOP
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class UnreleasedTopActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_unreleased_top

    override fun getToolbarTitle(): Int = R.string.title_unreleased_top

    override fun buttonAction() {
        val changes = DataGenerator.createWithUnreleasedChanges()
        pinNoticeBoard(Dynamic(changes), unreleasedPosition = TOP)
    }
}
