package com.example.ut7ej8estebanjosue.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej8estebanjosue.R
import com.example.ut7ej8estebanjosue.databinding.ActivityInicioSesionBinding
import com.example.ut7ej8estebanjosue.vista.dao.OperacionesDAO
import com.google.android.material.snackbar.Snackbar

class InicioSesion : AppCompatActivity() {
    lateinit var binding: ActivityInicioSesionBinding
    lateinit var bd: OperacionesDAO
    var usus = mutableListOf(R.array.Usuario1, R.array.Usuario2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_inicio_sesion)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bd = OperacionesDAO(this)

        binding.btnValidar.setOnClickListener {
            var user = binding.txtUser.text.toString()
            var contra = binding.txtPass.text.toString()
            //devuelve el login --> codigo = login o empty

            if (user.isEmpty() || contra.isEmpty()) {

                Snackbar.make(
                    binding.root, "Debe rellenar ambos campos",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {

                val usuarioIn = bd.comprobarCredenciales(user, contra)

                if (usuarioIn == null) {

                    Snackbar.make(
                        binding.root, "Usuario no encontrado",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }else{

                    var intent = Intent(this, MenuOpciones::class.java)
                    intent.putExtra("id", usuarioIn.id)
                    intent.putExtra("perfil", usuarioIn.perfil)
                    startActivity(intent)
                }


            }
        }

    }
}