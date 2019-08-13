package net.furkanakdemir.noticeboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.data.NoticeBoardRepository
import net.furkanakdemir.noticeboard.data.Repository

class NoticeBoardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: NoticeBoardAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        val repository: Repository = NoticeBoardRepository()


        viewManager = LinearLayoutManager(this)

        viewAdapter = NoticeBoardAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.change_recyclerview).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        viewAdapter.releaseList = repository.getChanges().toMutableList()
    }

}
