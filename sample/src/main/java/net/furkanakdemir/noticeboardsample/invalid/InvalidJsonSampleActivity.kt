package net.furkanakdemir.noticeboardsample.invalid

import net.furkanakdemir.noticeboard.Source.Json
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class InvalidJsonSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_json_invalid

    override fun getToolbarTitle(): Int = R.string.title_json_invalid

    override fun buttonAction() {
        val filepath = "sample_invalid.json"
        pinNoticeBoard(Json(filepath))
    }
}
