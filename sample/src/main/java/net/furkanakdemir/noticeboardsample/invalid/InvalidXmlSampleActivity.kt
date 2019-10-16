package net.furkanakdemir.noticeboardsample.invalid

import net.furkanakdemir.noticeboard.Source.Xml
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class InvalidXmlSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml

    override fun getToolbarTitle(): Int = R.string.title_xml

    override fun buttonAction() {
        val filepath = "sample_empty_file.xml"
        pinNoticeBoard(Xml(filepath))
    }
}
