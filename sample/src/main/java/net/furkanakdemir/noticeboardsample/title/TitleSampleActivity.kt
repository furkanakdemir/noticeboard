package net.furkanakdemir.noticeboardsample.title

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class TitleSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_title

    override fun getToolbarTitle(): Int = R.string.title_custom_title

    override fun buttonAction() {
        val source = Dynamic(DataGenerator.createChanges())
        pinNoticeBoard(source, title = getString(R.string.custom_title))
    }
}
