package net.furkanakdemir.noticeboardsample.util

import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboardsample.R

class TitleColorProvider : NoticeBoardColorProvider() {

    override var colorTitleDialog: Int = R.color.colorTitleCustom
    override var colorTitleActivity: Int = R.color.colorTitleCustom
}
