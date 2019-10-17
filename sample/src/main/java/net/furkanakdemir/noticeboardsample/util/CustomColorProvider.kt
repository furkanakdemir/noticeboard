package net.furkanakdemir.noticeboardsample.util

import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboardsample.R

class CustomColorProvider : NoticeBoardColorProvider() {

    override var colorAdded: Int = R.color.colorAccent
    override var colorChanged: Int = R.color.colorAccent
    override var colorDeprecated: Int = R.color.colorPrimary
    override var colorRemoved: Int = R.color.colorPrimary
    override var colorFixed: Int = R.color.colorPrimaryDark
    override var colorSecurity: Int = R.color.colorPrimaryDark
}
