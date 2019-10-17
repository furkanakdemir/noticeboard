package net.furkanakdemir.noticeboardsample.color

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.CustomColorProvider
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class BackgroundColorActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_color_change_type

    override fun getToolbarTitle(): Int = R.string.title_custom_color_change_type

    override fun buttonAction() {
        val customColorProvider = CustomColorProvider()
        val source = Dynamic(DataGenerator.createChanges())
        pinNoticeBoard(source, customColorProvider)
    }
}
