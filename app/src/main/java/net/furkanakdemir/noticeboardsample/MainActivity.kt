package net.furkanakdemir.noticeboardsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.furkanakdemir.noticeboard.NoticeBoard

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val noticeBoard = NoticeBoard.create {
                context(this@MainActivity)
                source(NoticeBoard.Source.DYNAMIC)
            }

            noticeBoard.pin()
        }
    }

}
