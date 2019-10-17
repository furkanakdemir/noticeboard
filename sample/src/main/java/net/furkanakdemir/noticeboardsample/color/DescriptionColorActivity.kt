package net.furkanakdemir.noticeboardsample.color

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity
import net.furkanakdemir.noticeboardsample.util.DataGenerator
import net.furkanakdemir.noticeboardsample.util.DescriptionColorProvider

class DescriptionColorActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_color_description

    override fun getToolbarTitle(): Int = R.string.title_custom_color_description

    override fun buttonAction() {
        val customColorProvider = DescriptionColorProvider()
        val source = Dynamic(DataGenerator.createChanges())
        pinNoticeBoard(source, customColorProvider)
    }
}
