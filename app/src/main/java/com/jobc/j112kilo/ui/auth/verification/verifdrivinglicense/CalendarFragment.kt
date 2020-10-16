package com.jobc.j112kilo.ui.auth.verification.verifdrivinglicense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jobc.j112kilo.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.btnBack

const val CALENDAR_DATA = "calendar_data"

class CalendarFragment : Fragment() {

    private lateinit var navController: NavController
    private var selectDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendarView()
        initNavController(view)
        initButton()
    }

    private fun initNavController(view: View) {
        navController = view.findNavController()
    }

    private fun initCalendarView() {
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            var monthStr = "$month"
            var dayOfMonthStr = "$dayOfMonth"
            if("${month + 1}".length == 1) {
                monthStr = "0${month + 1}"
            }
            if("$dayOfMonth".length == 1) {
                dayOfMonthStr = "0$dayOfMonth"
            }
            selectDate = "$dayOfMonthStr.$monthStr.$year"
        }
    }

    private fun initButton() {
        initBtnOk()
        initBtnCancel()
        initBtnBack()
    }

    private fun initBtnOk() {
        btnOk.setOnClickListener {
            selectDate?.let {
                navController.previousBackStackEntry?.savedStateHandle?.set(CALENDAR_DATA, it)
                activity?.onBackPressed()
            }
        }
    }

    private fun initBtnCancel() {
        btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initBtnBack() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}