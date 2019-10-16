package net.furkanakdemir.noticeboardsample.empty

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class EmptyDynamicSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_dynamic

    override fun getToolbarTitle(): Int = R.string.title_dynamic

    override fun buttonAction() {
        pinNoticeBoard(Dynamic())
    }
}
