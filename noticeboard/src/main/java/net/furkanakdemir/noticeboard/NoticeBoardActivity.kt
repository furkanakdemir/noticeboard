package net.furkanakdemir.noticeboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.data.InMemoryNoticeBoardRepository
import net.furkanakdemir.noticeboard.data.NoticeBoardRepository
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider

class NoticeBoardActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: NoticeBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        setupToolbar()

        val noticeBoardRepository: NoticeBoardRepository = InMemoryNoticeBoardRepository

        viewAdapter = NoticeBoardAdapter(NoticeBoardColorProvider(this))

        recyclerView = findViewById<RecyclerView>(R.id.change_recyclerview).apply {
            setHasFixedSize(true)
            adapter = viewAdapter

        }

        viewAdapter.releaseList = noticeBoardRepository.getChanges().toMutableList()
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
