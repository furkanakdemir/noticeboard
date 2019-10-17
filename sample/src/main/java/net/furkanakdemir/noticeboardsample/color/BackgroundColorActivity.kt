package net.furkanakdemir.noticeboardsample.color

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.CustomBackgroundColorProvider
import net.furkanakdemir.noticeboardsample.util.DataGenerator

class BackgroundColorActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_color_background

    override fun getToolbarTitle(): Int = R.string.title_custom_color_background

    override fun buttonAction() {
        val customColorProvider = CustomBackgroundColorProvider()
        val source = Dynamic(DataGenerator.createChanges())
        pinNoticeBoard(source, customColorProvider)
    }
}
