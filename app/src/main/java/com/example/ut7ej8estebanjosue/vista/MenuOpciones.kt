package com.example.ut7ej8estebanjosue.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ActivityMainBinding
import com.example.ut7ej8estebanjosue.databinding.ActivityMenuOpcionesBinding

class MenuOpciones : AppCompatActivity() {
    private lateinit var mBinding:ActivityMenuOpcionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMenuOpcionesBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        var datoId = intent.getIntExtra("id",0)
        var datoPerfil = intent.getStringExtra("perfil")

        if(datoPerfil.equals("U")){
            mBinding.btnAlta.isVisible = false
            mBinding.btnModif.isVisible = false
        }

        mBinding.btnAlta.setOnClickListener {
            val intent = Intent(this, AltaEvento::class.java)
            startActivity(intent)
        }

        mBinding.btnModif.setOnClickListener{
            val intent = Intent(this, ModificarEvento::class.java)
            startActivity(intent)
        }

        mBinding.btnConsultar.setOnClickListener{
            val intent = Intent(this, ConsultaEvento::class.java)
            intent.putExtra("idUsuario", datoId)
            startActivity(intent)
        }


    }
}