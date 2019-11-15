package de.fklappan.app.webnotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.leinardi.android.speeddial.SpeedDialView

import de.fklappan.app.webnotes.R
import de.fklappan.app.webnotes.common.mvx.BaseObservableMvxView

class DetailView(inflater: LayoutInflater, viewGroup: ViewGroup?) : BaseObservableMvxView<DetailContract.ViewListener>(), DetailContract.View {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewContent: TextView
    private lateinit var fabView: SpeedDialView

    init {
        rootView = inflater.inflate(R.layout.detailview, viewGroup)
        initView()
    }

    private fun initView() {
        textViewTitle = rootView.findViewById(R.id.textViewTitle)
        textViewContent = rootView.findViewById(R.id.textViewContent)
        fabView = rootView.findViewById(R.id.fab)
        fabView.setOnChangeListener(object : SpeedDialView.OnChangeListener {

            override fun onMainActionSelected(): Boolean {
                for(listener in listeners) {
                    listener.onEditClicked()
                }
                return false
            }

            override fun onToggleChanged(isOpen: Boolean) {

            }

        })
    }

    override fun getViewState(): Bundle? {
        return null
    }

    override fun cleanup() {
    }

    override fun setTitle(title: String) {
        textViewTitle.text = title
    }

    override fun setContent(content: String) {
        textViewContent.text = content
    }

    override fun startEditMode() {
    }

}
