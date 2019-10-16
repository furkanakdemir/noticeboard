package net.furkanakdemir.noticeboardsample.sourcetype

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class DynamicSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_dynamic

    override fun getToolbarTitle(): Int = R.string.title_dynamic

    override fun buttonAction() {
        val changes = DataGenerator.createChanges()
        pinNoticeBoard(Dynamic(changes))
    }
}
