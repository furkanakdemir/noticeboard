package net.furkanakdemir.noticeboardsample.empty

import net.furkanakdemir.noticeboard.Source.Xml
import net.furkanakdemir.noticeboardsample.R
import net.furkanakdemir.noticeboardsample.base.BaseSampleActivity

class EmptyXmlSampleActivity : BaseSampleActivity() {

    override fun getDescription(): Int = R.string.description_xml_empty

    override fun getToolbarTitle(): Int = R.string.title_xml_empty

    override fun buttonAction() {
        val filepath = "sample_empty.xml"
        pinNoticeBoard(Xml(filepath))
    }
}
