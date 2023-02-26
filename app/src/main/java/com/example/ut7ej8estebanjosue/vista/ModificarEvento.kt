package com.example.ut7ej8estebanjosue.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ActivityModificarEventoBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ModificarEvento : AppCompatActivity() {

    private lateinit var binding: ActivityModificarEventoBinding
    private lateinit var bd: OperacionesDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModificarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtFecha2.setOnClickListener { showDatePickerDialog2() }
        binding.txtHora2.setOnClickListener { showTimePickerDialog2() }

        binding.btnModificarEvento .setOnClickListener {
            modificarEvento()
        }
        binding.btnCancel2.setOnClickListener {
            finish()
        }

    }

    private fun modificarEvento() {

        var fecha = binding.txtFecha2.text.toString()
        var hora = binding.txtHora2.text.toString()
        var nombre = binding.txtNombreEvento2.text.toString()

        if (fecha.isEmpty() || hora.isEmpty() || nombre.isEmpty()) {
            Snackbar.make(binding.root, "Debe rellenar todos los campos)", Snackbar.LENGTH_SHORT)
                .show()
        } else if (bd.existeUnEvento(fecha, hora)) {
            Snackbar.make(binding.root, "Ya existe un evento en el fecha y hora introducidos", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            bd.updateUnEvento(nombre, fecha, hora)
            Toast.makeText(this, "Evento actualizado con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun showDatePickerDialog2() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDaySelected2(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDaySelected2(day: Int, month: Int, year: Int) {

        binding.txtFecha2.setText("$year/${month + 1}/$day")

    }

    private fun showTimePickerDialog2() {

        //Como solo devolvemos un parametro ponemos 'it' (valor sobre ele que iteramos)
        val timePicker = TimePickerFragment { onTimeSelected2(it) }
        timePicker.show(supportFragmentManager, "time")

    }

    private fun onTimeSelected2(time:String) {

        binding.txtHora2.setText("$time")

    }



}