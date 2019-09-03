package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.NoticeBoard
import net.furkanakdemir.noticeboard.NoticeBoardItem
import net.furkanakdemir.noticeboard.Source

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val items = mutableListOf(
            NoticeBoardItem.ReleaseHeader("16 Sep 2019", "2.0.0"),
            NoticeBoardItem.ChangeItem(ChangeType.ADDED, "New Login Page"),
            NoticeBoardItem.ChangeItem(ChangeType.FIXED, "Crash in Payment")
        )


        NoticeBoard(this@MainActivity).pin {
            source(Source.Dynamic(items))
        }
    }
}
