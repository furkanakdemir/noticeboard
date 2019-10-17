package net.furkanakdemir.noticeboardsample.color

import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseListSampleActivity
import net.furkanakdemir.noticeboardsample.color.ColorSampleActivity.Color.BACKGROUND
import net.furkanakdemir.noticeboardsample.color.ColorSampleActivity.Color.CHANGE_TYPE
import net.furkanakdemir.noticeboardsample.color.ColorSampleActivity.Color.DESCRIPTION
import net.furkanakdemir.noticeboardsample.color.ColorSampleActivity.Color.TITLE
import net.furkanakdemir.noticeboardsample.ui.SampleItem
import net.furkanakdemir.noticeboardsample.ui.SampleItem.Sample
import net.furkanakdemir.noticeboardsample.util.ext.launch

class ColorSampleActivity : BaseListSampleActivity() {

    override fun createSamples(): List<SampleItem> = Color.values().map { Sample(it.type) }

    override fun getToolbarTitle(): Int = R.string.title_custom_color

    override fun getOnClickListener(): (SampleItem) -> Unit = {
        when (it.title) {
            CHANGE_TYPE.type -> launch<ChangeTypeColorActivity>()
            BACKGROUND.type -> launch<BackgroundColorActivity>()
            DESCRIPTION.type -> launch<DescriptionColorActivity>()
            TITLE.type -> launch<TitleColorActivity>()
        }
    }

    enum class Color(val type: String) {
        CHANGE_TYPE("Change Type"),
        BACKGROUND("Background"),
        DESCRIPTION("Description"),
        TITLE("Title")
    }
}
