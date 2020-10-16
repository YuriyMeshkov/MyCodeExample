package com.jobc.j112kilo.ui.auth.registrationlogin.registration.registrationaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.jobc.j112kilo.R
import com.jobc.j112kilo.utils.ERROR_DATA
import kotlinx.android.synthetic.main.fragment_content_viewing.*


class ContentViewingFragment : Fragment() {

    companion object {
        private const val CONTENT_FOR_VIEW = "content_for_view"

        fun newArgument(content: String) =
            Bundle().apply {
                putString(CONTENT_FOR_VIEW, content)

        }
    }

    private lateinit var content: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_viewing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        initButtons()
    }

    private fun getDataFromArgument() {
        arguments?.let {
            content = it.getString(CONTENT_FOR_VIEW) ?: ERROR_DATA
        }
    }

    private fun initWebView() {
        wbContent.settings.javaScriptEnabled = true
        wbContent.webViewClient = WebViewClient()
        wbContent.loadUrl(content)
    }

    private fun initButtons() {
        initBtnBack()
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}