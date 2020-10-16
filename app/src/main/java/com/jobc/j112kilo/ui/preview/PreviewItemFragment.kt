package com.jobc.j112kilo.ui.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jobc.j112kilo.R
import kotlinx.android.synthetic.main.fragment_preview_item.*

class PreviewItemFragment  : Fragment() {

    companion object {
        fun newInstance(image: Int, title: String, text: String) : PreviewItemFragment {
            val fragment = PreviewItemFragment()
            fragment.setViewFragment(image, title, text)
            return fragment
        }
    }

    private var image: Int? = null
    private var title: String? = null
    private var text: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataToView()
    }

    private fun loadDataToView() {
        image?.let {
            imagePreview.setImageResource(it)
        }
        title?.let {
            textTitle.text = it
        }
        text?.let {
            textPreview.text = it
        }
    }

    private fun setViewFragment(image: Int, title: String, text: String) {
        this.image = image
        this.title = title
        this.text = text
    }
}