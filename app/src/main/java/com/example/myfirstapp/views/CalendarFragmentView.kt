package com.example.myfirstapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.myfirstapp.R
import java.util.Calendar

class CalendarFragmentView : Fragment() {
    private lateinit var calendar: CalendarView
    private lateinit var calendarHandler: CalendarDateHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar_view, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.calendar = view.findViewById(R.id.calendar)

        this.setUpCalendarView()
        this.observeCalendarDateChanges()
    }

    fun setUpCalendarView(){
        this.calendar.setDate(System.currentTimeMillis(), false, true)
    }

    fun setUpCalendarHandler(handler: CalendarDateHandler) {
        this.calendarHandler = handler
    }


    private fun observeCalendarDateChanges() {
        this.calendar.setOnDateChangeListener { calendar: CalendarView, year: Int, month: Int, day: Int ->
            val calendar: Calendar = Calendar.getInstance()

            calendar.set(year, month, day)
            this.calendar.setDate(calendar.timeInMillis, false, true)

            // Notify activity
            this.calendarHandler.getSelectedDate(day, month + 1, year)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CalendarFragmentView().apply {
                arguments = Bundle().apply {

                }
            }
    }
}