package net.furkanakdemir.noticeboard.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.InternalNoticeBoard
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.KEY_TITLE
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.TITLE_DEFAULT
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.result.EventObserver

internal class NoticeBoardDialogFragment : DialogFragment() {

    private var recyclerView: RecyclerView? = null
    private var messageTextView: TextView? = null
    private lateinit var noticeBoardAdapter: NoticeBoardAdapter

    private lateinit var noticeBoardViewModel: NoticeBoardViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val title = arguments?.getString(KEY_TITLE, TITLE_DEFAULT)

        builder.setTitle(title).setPositiveButton(
            getString(R.string.dialog_notice_board_close)
        ) { _, _ -> dismiss() }

        val view = buildView()
        builder.setView(view)

        setupViewModel()

        return builder.create()
    }

    private fun setupViewModel() {

        noticeBoardViewModel =
            ViewModelProviders.of(this).get(NoticeBoardViewModel::class.java)

        noticeBoardViewModel.releaseLiveData.observe(this, Observer {
            noticeBoardAdapter.releaseList = it.toMutableList()
            showContent()
        })

        noticeBoardViewModel.eventLiveData.observe(this, EventObserver {
            messageTextView?.text = it
            showMessage()
        })

        noticeBoardViewModel.getChanges()
    }

    private fun buildView(): View? {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_notice_board, null)
        noticeBoardAdapter = NoticeBoardAdapter(InternalNoticeBoard.getColorProvider())

        recyclerView = view.findViewById(R.id.change_recyclerview)
        messageTextView = view.findViewById(R.id.messageTextView)
        recyclerView?.apply {
            setHasFixedSize(true)
            adapter = noticeBoardAdapter
        }
        return view
    }

    private fun showMessage() {
        messageTextView?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
    }

    private fun showContent() {
        messageTextView?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(title: String): NoticeBoardDialogFragment {
            val frag = NoticeBoardDialogFragment()
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            frag.arguments = args
            return frag
        }
    }
}
