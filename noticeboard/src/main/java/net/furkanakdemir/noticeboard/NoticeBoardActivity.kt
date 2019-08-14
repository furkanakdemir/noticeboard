package net.furkanakdemir.noticeboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.data.NoticeBoardRepository
import net.furkanakdemir.noticeboard.data.Repository
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider

class NoticeBoardActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: NoticeBoardAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        setupToolbar()

        val repository: Repository = NoticeBoardRepository()


        viewManager = LinearLayoutManager(this)

        viewAdapter = NoticeBoardAdapter(NoticeBoardColorProvider(this))

        recyclerView = findViewById<RecyclerView>(R.id.change_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

        viewAdapter.releaseList = repository.getChanges().toMutableList()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.notice_board_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = TITLE_DEFAULT
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val TITLE_DEFAULT = "NoticeBoard"
    }
}
