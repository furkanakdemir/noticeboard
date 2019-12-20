package net.furkanakdemir.noticeboardsample.invalid

import net.furkanakdemir.noticeboard.Source.Json
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class InvalidCustomPlaceholderSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_custom_placeholder_error

    override fun getToolbarTitle(): Int = R.string.title_custom_placeholder_error

    override fun buttonAction() {
        val filepath = "sample_invalid.json"
        pinNoticeBoard(
            Json(filepath),
            errorText = "Custom Invalid",
            errorIcon = R.drawable.ic_info
        )
    }
}
