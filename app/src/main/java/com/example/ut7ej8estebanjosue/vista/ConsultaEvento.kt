package com.example.ut7ej8estebanjosue.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ActivityConsultaEventoBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO
import com.example.ut7ej8estebanjosue.vista.modelo.AdaptadorEventos
import com.example.ut7ej8estebanjosue.vista.modelo.EventosInterface

class ConsultaEvento : AppCompatActivity(), EventosInterface {

    private lateinit var binding: ActivityConsultaEventoBinding
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConsultaEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bd = OperacionesDAO(this)


        configurarRecycler()
    }

    private fun configurarRecycler() {

        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = AdaptadorEventos(bd.listaEventos(), this)
        binding.recyclerview.adapter?.notifyDataSetChanged()

    }

    /*override fun onResume() {
        super.onResume()
        binding.recyclerview.adapter = AdaptadorEventos(bd.listaEventos(), this)
        binding.recyclerview.adapter?.notifyDataSetChanged()
    }*/

    override fun pulsacionLarga(idEvento: Int): Boolean {

        var idUsuario = intent.getIntExtra("idUsuario", 0)

        val intent = Intent(this, VisualDatosEvento::class.java)
        intent.putExtra("codUsuario",idUsuario)
        intent.putExtra("codEvento", idEvento)
        startActivity(intent)

        return true

    }
}