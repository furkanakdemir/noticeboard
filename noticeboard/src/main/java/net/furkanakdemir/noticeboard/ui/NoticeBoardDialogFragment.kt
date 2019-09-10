package net.furkanakdemir.noticeboard.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.KEY_TITLE
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.TITLE_DEFAULT
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.di.DaggerInjector
import net.furkanakdemir.noticeboard.result.EventObserver
import net.furkanakdemir.noticeboard.util.color.NoticeBoardColorProvider
import javax.inject.Inject


class NoticeBoardDialogFragment : DialogFragment() {

    private var recyclerView: RecyclerView? = null
    private var messageTextView: TextView? = null
    private lateinit var noticeBoardAdapter: NoticeBoardAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var noticeBoardViewModel: NoticeBoardViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val title = arguments?.getString(KEY_TITLE, TITLE_DEFAULT)

        builder.setTitle(title).setPositiveButton(
            getString(R.string.dialog_notice_board_close)
        ) { p0, p1 -> dismiss() }

        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_notice_board, null)
        noticeBoardAdapter = NoticeBoardAdapter(NoticeBoardColorProvider(requireContext()))

        recyclerView = view.findViewById(R.id.change_recyclerview)
        messageTextView = view.findViewById(R.id.messageTextView)
        recyclerView?.apply {
            setHasFixedSize(true)
            adapter = noticeBoardAdapter
        }
        builder.setView(view)

        DaggerInjector.component?.inject(this)
        noticeBoardViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoticeBoardViewModel::class.java)

        noticeBoardViewModel.releaseLiveData.observe(this, Observer {
            noticeBoardAdapter.releaseList = it.toMutableList()
            showContent()
        })

        noticeBoardViewModel.eventLiveData.observe(this, EventObserver {
            messageTextView?.text = it
            showMessage()
        })

        noticeBoardViewModel.getChanges()

        return builder.create()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        dialog?.let {
            val width = resources.getDimensionPixelSize(R.dimen.width_dialog)
            val height = resources.getDimensionPixelSize(R.dimen.height_dialog)
            dialog.window?.setLayout(width, height)
        }
    }

    private fun showMessage() {
        messageTextView?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE

    }

    private fun showContent() {
        messageTextView?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()

        dialog?.let {
            val width = resources.getDimensionPixelSize(R.dimen.width_dialog)
            val height = resources.getDimensionPixelSize(R.dimen.height_dialog)
            dialog.window?.setLayout(width, height)
        }
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