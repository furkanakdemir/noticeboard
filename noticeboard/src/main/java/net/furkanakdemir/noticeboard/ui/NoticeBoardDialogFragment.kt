package net.furkanakdemir.noticeboard.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import net.furkanakdemir.noticeboard.DialogNoticeBoardBehavior
import net.furkanakdemir.noticeboard.DisplayOptions.DIALOG
import net.furkanakdemir.noticeboard.InternalNoticeBoard
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.KEY_TITLE
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.TITLE_DEFAULT
import net.furkanakdemir.noticeboard.NoticeBoardBehavior
import net.furkanakdemir.noticeboard.R
import net.furkanakdemir.noticeboard.result.EventObserver
import net.furkanakdemir.noticeboard.ui.NoticeBoardViewModel.ViewEvent.Empty
import net.furkanakdemir.noticeboard.ui.NoticeBoardViewModel.ViewEvent.Error
import net.furkanakdemir.noticeboard.util.ext.getColorId

internal class NoticeBoardDialogFragment : DialogFragment() {

    private var recyclerView: RecyclerView? = null
    private var messageTextView: TextView? = null

    private lateinit var noticeBoardAdapter: NoticeBoardAdapter

    private lateinit var noticeBoardBehavior: NoticeBoardBehavior
    private val colorProvider = InternalNoticeBoard.getInstance().getColorProvider()

    private val noticeBoardViewModel by viewModels<NoticeBoardViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.NoticeBoard_Dialog)

        val title = arguments?.getString(KEY_TITLE, TITLE_DEFAULT).orEmpty()

        builder.setTitle(title).setPositiveButton(
            getString(R.string.dialog_notice_board_close)
        ) { _, _ -> dismiss() }

        val view = buildView()
        builder.setView(view)

        val titleRootView =
            requireActivity().layoutInflater.inflate(R.layout.layout_title_dialog, null)
        val titleTextView = titleRootView.findViewById<TextView>(R.id.titleDialogTextView)

        builder.setCustomTitle(titleRootView)

        setupViewModel()

        val dialog = builder.create()
        noticeBoardBehavior = DialogNoticeBoardBehavior(dialog, titleTextView)

        val backgroundColorId = requireContext().getColorId(colorProvider.getBackgroundColor())
        noticeBoardBehavior.setBackgroundColor(backgroundColorId)

        val titleColorId = colorProvider.getTitleColor(DIALOG)
        noticeBoardBehavior.setTitleColor(requireContext().getColorId(titleColorId))
        noticeBoardBehavior.setTitleText(title)

        return dialog
    }

    private fun setupViewModel() {

        noticeBoardViewModel.releaseLiveData.observe(this, Observer {
            noticeBoardAdapter.releaseList = it.toMutableList()
            showContent()
        })

        noticeBoardViewModel.eventLiveData.observe(this, EventObserver {
            when (it) {
                Empty -> {
                    val emptyText =
                        InternalNoticeBoard.getInstance(requireContext()).getEmptyText()
                    messageTextView?.text = emptyText

                    val emptyIcon = InternalNoticeBoard.getInstance(requireContext()).getEmptyIcon()
                    messageTextView?.setCompoundDrawablesWithIntrinsicBounds(0, emptyIcon, 0, 0)
                }

                Error -> {
                    val errorText =
                        InternalNoticeBoard.getInstance(requireContext()).getErrorText()
                    messageTextView?.text = errorText

                    val errorIcon = InternalNoticeBoard.getInstance(requireContext()).getErrorIcon()
                    messageTextView?.setCompoundDrawablesWithIntrinsicBounds(0, errorIcon, 0, 0)
                }
            }

            showMessage()
        })

        noticeBoardViewModel.getChanges()
    }

    private fun buildView(): View? {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_notice_board, null)
        noticeBoardAdapter = NoticeBoardAdapter(colorProvider)

        recyclerView = view.findViewById(R.id.changeRecyclerView)
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
