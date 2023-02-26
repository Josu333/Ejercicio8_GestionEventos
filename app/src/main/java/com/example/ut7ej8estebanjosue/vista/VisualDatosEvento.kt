package com.example.ut7ej8estebanjosue.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej8estebanjosue.databinding.ActivityVisualDatosEventoBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO
import com.google.android.material.snackbar.Snackbar

class VisualDatosEvento : AppCompatActivity() {
    lateinit var binding: ActivityVisualDatosEventoBinding
    lateinit var bd: OperacionesDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisualDatosEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bd = OperacionesDAO(this)

        var codigoEvento = intent.getIntExtra("codEvento",0)
        var codigoUsuario = intent.getIntExtra("codUsuario", 0)

        val eventoSelected = bd.eventoSelected(codigoEvento)

        binding.fecha.text = eventoSelected!!.fecha
        binding.hora.text = eventoSelected!!.hora
        binding.nombre.text = eventoSelected!!.nombre
        binding.descripcion.text = eventoSelected!!.descripcion

        binding.btnApuntarse.setOnClickListener { apuntarse(codigoUsuario, codigoEvento) }
        binding.btnAnular.setOnClickListener { desapuntarse(codigoUsuario, codigoEvento) }

    }

    private fun desapuntarse(codigoUsuario: Int, codigoEvento: Int) {

        if (!bd.existeEU(codigoUsuario, codigoEvento)) {
            Snackbar.make(binding.root, "El usuario no se encontraba inscrito", Snackbar.LENGTH_SHORT).show()
        } else {
            bd.eliminarEU(codigoUsuario, codigoEvento)
            Snackbar.make(binding.root, "Inscripción cancelada", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun apuntarse(codigoUsuario: Int, codigoEvento: Int) {

        if (!bd.existeEU(codigoUsuario, codigoEvento)) {
            bd.anadirEU(codigoUsuario, codigoEvento)
            Snackbar.make(binding.root, "Se ha inscrito al evento con éxito !", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, "El usuario ya está inscrito a este evento", Snackbar.LENGTH_SHORT).show()
        }

    }
}