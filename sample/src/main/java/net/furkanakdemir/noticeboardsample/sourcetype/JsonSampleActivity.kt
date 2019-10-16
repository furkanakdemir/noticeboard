package net.furkanakdemir.noticeboardsample.sourcetype

import net.furkanakdemir.noticeboard.Source.Json
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class JsonSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_json

    override fun getToolbarTitle(): Int = R.string.title_json

    override fun buttonAction() {
        val filepath = "sample.json"
        pinNoticeBoard(Json(filepath))
    }
}
