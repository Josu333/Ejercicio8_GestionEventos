package com.example.ut7ej8estebanjosue.vista.modelo

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ItemEventosBinding

class AdaptadorEventos (private var eventos: MutableList<Eventos>,  private val listener: EventosInterface)
    : RecyclerView.Adapter<AdaptadorEventos.ViewHolder>()  {

    private lateinit var contexto: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEventosBinding.bind(view)

        fun setListener(evento:Eventos) {
            with(binding.root){
                setOnLongClickListener {
                    listener.pulsacionLarga(evento.id!! )
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contexto = parent.context
        val layoutInflater = LayoutInflater.from(contexto)
        return ViewHolder(layoutInflater.inflate(R.layout.item_eventos,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val evento = eventos.get(position)

        with(holder){
            setListener(evento)
            binding.titulo.text = evento.nombre
            binding.descripcion.text=evento.descripcion
        }

    }

    override fun getItemCount(): Int = eventos.size

    }


