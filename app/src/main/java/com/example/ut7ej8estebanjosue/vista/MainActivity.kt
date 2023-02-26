package com.example.ut7ej8estebanjosue.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ActivityMainBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd = OperacionesDAO(this)
        pantallaTemporal()


    }


    private fun pantallaTemporal() {
        Thread {
            val baseVacia = bd.baseVacia()
            if (baseVacia) {
                anadirUsuarios()
            }

            Thread.sleep(2000)
            val intent = Intent(this@MainActivity, InicioSesion::class.java)
            startActivity(intent)
            finish()

        }.start()
    }

    fun anadirUsuarios() {

        val usuario1 = resources.getStringArray(R.array.Usuario1)
        val usuario2 = resources.getStringArray(R.array.Usuario2)


        Thread {
            bd.addUsuario(usuario1)
            bd.addUsuario(usuario2)
        }.start()


    }

}