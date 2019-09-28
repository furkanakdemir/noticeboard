package net.furkanakdemir.noticeboard.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_notice_board.*
import net.furkanakdemir.noticeboard.InternalNoticeBoard
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.KEY_TITLE
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.result.EventObserver

internal class NoticeBoardActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var noticeBoardAdapter: NoticeBoardAdapter

    private val noticeBoardViewModel by viewModels<NoticeBoardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_notice_board)

        setupToolbar()

        setupRecyclerView()

        noticeBoardViewModel.releaseLiveData.observe(this, Observer {
            noticeBoardAdapter.releaseList = it.toMutableList()
            showContent()
        })

        noticeBoardViewModel.eventLiveData.observe(this, EventObserver {
            messageTextView.text = it
            showMessage()
        })

        noticeBoardViewModel.getChanges()
    }

    private fun showMessage() {
        messageTextView.visibility = VISIBLE
        recyclerView.visibility = GONE
    }

    private fun showContent() {
        messageTextView.visibility = GONE
        recyclerView.visibility = VISIBLE
    }

    private fun setupRecyclerView() {
        noticeBoardAdapter = NoticeBoardAdapter(InternalNoticeBoard.getInstance(this).getColorProvider())

        recyclerView = findViewById<RecyclerView>(R.id.change_recyclerview).apply {
            setHasFixedSize(true)
            adapter = noticeBoardAdapter
        }
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.notice_board_toolbar)
        setSupportActionBar(toolbar)

        val title = intent.getStringExtra(KEY_TITLE)
            ?: throw IllegalStateException("field $KEY_TITLE missing in Intent")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun createIntent(context: Context, title: String): Intent {
            val intent = Intent(context, NoticeBoardActivity::class.java)
            intent.putExtra(KEY_TITLE, title)
            return intent
        }
    }
}
