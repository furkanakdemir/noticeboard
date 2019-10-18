package net.furkanakdemir.noticeboardsample.util

import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboardsample.R

class CustomBackgroundColorProvider : NoticeBoardColorProvider() {

    override var colorBackground: Int = R.color.colorBackgroundCustom
}
