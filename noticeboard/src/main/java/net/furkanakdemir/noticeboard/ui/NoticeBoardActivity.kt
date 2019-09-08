package net.furkanakdemir.noticeboard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.di.DaggerInjector
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import javax.inject.Inject

class NoticeBoardActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var noticeBoardAdapter: NoticeBoardAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var noticeBoardViewModel: NoticeBoardViewModel

    @Inject
    lateinit var viewMapper: Mapper<List<Release>, List<NoticeBoardItem>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerInjector.component?.inject(this)

        setContentView(R.layout.activity_notice_board)
        noticeBoardViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoticeBoardViewModel::class.java)

        setupToolbar()

        setupRecyclerView()

        noticeBoardViewModel.releaseLiveData.observe(this, Observer {
            noticeBoardAdapter.releaseList = it.toMutableList()
        })

        noticeBoardViewModel.getChanges()
    }

    private fun setupRecyclerView() {
        noticeBoardAdapter =
            NoticeBoardAdapter(NoticeBoardColorProvider(this))

        recyclerView = findViewById<RecyclerView>(R.id.change_recyclerview).apply {
            setHasFixedSize(true)
            adapter = noticeBoardAdapter
        }
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
