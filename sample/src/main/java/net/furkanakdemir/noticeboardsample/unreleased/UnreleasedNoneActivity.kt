package net.furkanakdemir.noticeboardsample.unreleased

import net.furkanakdemir.noticeboard.Position.NONE
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class UnreleasedNoneActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml

    override fun getToolbarTitle(): Int = R.string.title_xml

    override fun buttonAction() {
        val changes = DataGenerator.createWithUnreleasedChanges()
        pinNoticeBoard(Dynamic(changes), unreleasedPosition = NONE)
    }
}
