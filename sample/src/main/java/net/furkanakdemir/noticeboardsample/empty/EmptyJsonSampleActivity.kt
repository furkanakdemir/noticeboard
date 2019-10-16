package net.furkanakdemir.noticeboardsample.empty

import net.furkanakdemir.noticeboard.Source.Json
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class EmptyJsonSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_json

    override fun getToolbarTitle(): Int = R.string.title_json

    override fun buttonAction() {
        val filepath = "sample_empty.json"
        pinNoticeBoard(Json(filepath))
    }
}
