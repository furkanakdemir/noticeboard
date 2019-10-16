package net.furkanakdemir.noticeboardsample.sourcetype

import net.furkanakdemir.noticeboard.Source.Xml
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class XmlSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml

    override fun getToolbarTitle(): Int = R.string.title_xml

    override fun buttonAction() {
        val filepath = "sample.xml"
        pinNoticeBoard(Xml(filepath))
    }
}
