package com.example.ut7ej8estebanjosue.vista


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ut7ej8estebanjosue.databinding.ActivityAltaEventoBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO
import com.example.ut7ej8estebanjosue.vista.modelo.Eventos
import com.google.android.material.snackbar.Snackbar

class AltaEvento : AppCompatActivity() {
    private lateinit var binding: ActivityAltaEventoBinding
    private lateinit var bd: OperacionesDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bd = OperacionesDAO(this)

        binding.txtFecha.setOnClickListener { showDatePickerDialog() }
        binding.txtHora.setOnClickListener { showTimePickerDialog() }

        binding.btnAgregarEvento.setOnClickListener {
            agregarEvento()
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }

    }

    private fun agregarEvento() {

        val fecha = binding.txtFecha.text.toString().trim()
        val hora = binding.txtHora.text.toString().trim()
        val nombre = binding.txtNombreEvento.text.toString().trim()
        val descrip = binding.txtDescripcion.text.toString().trim()

        if (fecha.isEmpty() || hora.isEmpty() || nombre.isEmpty() || descrip.isEmpty()) {
            Snackbar.make(binding.root,
                "No debe haber campos vacios",
                Snackbar.LENGTH_SHORT)
                .show()
        }else if (bd.existeUnEvento(fecha, hora)) {
            Snackbar.make(binding.root,
                "Ya existe un evento en esa fecha y hora",
                Snackbar.LENGTH_SHORT)
                .show()
        }else {
            val newEvento = Eventos(null, fecha, hora, nombre, descrip)
            bd.addEvento(newEvento)


            Toast.makeText(this,
                "Evento insertado con éxito !",
                Toast.LENGTH_SHORT).show()
            finish()
        }


    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDaySelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDaySelected(day: Int, month: Int, year: Int) {

        binding.txtFecha.setText("$year-${month + 1}-$day")

    }

    private fun showTimePickerDialog() {

        //Como solo devolvemos un parametro ponemos 'it' (valor sobre ele que iteramos)
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "time")

    }

    private fun onTimeSelected(time:String) {

        binding.txtHora.setText("$time")

    }
}