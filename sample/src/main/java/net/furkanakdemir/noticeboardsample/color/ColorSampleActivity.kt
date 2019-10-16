package net.furkanakdemir.noticeboardsample.color

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.CustomColorProvider
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class ColorSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_json

    override fun getToolbarTitle(): Int = R.string.title_json

    override fun buttonAction() {
        val customColorProvider = CustomColorProvider(this)
        val source = Dynamic(DataGenerator.createChanges())
        pinNoticeBoard(source, customColorProvider)
    }
}
