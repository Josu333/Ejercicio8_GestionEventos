package com.example.ut7ej8estebanjosue.vista

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.ut7ej8estebanjosue.R
import java.util.*

class TimePickerFragment (val listener: (String) -> Unit): DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        listener("$hourOfDay:$minute")

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val dialog = TimePickerDialog(activity as Context, R.style.datePickerTheme ,this, hour, minute, true)

        return dialog
    }
}