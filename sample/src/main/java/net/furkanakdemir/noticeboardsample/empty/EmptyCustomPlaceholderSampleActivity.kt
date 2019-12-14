package net.furkanakdemir.noticeboardsample.empty

import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class EmptyCustomPlaceholderSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_placeholder_empty

    override fun getToolbarTitle(): Int = R.string.title_custom_placeholder_empty

    override fun buttonAction() {
        pinNoticeBoard(
            Dynamic(),
            emptyText = "Custom Empty",
            emptyIcon = R.drawable.ic_info
        )
    }
}
