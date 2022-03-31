package com.example.saltamontes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.saltamontes.clases.Estudiante
import com.example.saltamontes.clases.Operaciones
import java.lang.NumberFormatException

class RegistroActivity : AppCompatActivity() {


    var campoNombre: EditText ?= null
    var campoDocumento: EditText ?= null
    var campoEdad: EditText ?= null
    var campoTelefono: EditText ?= null
    var campoDireccion: EditText ?= null
    var campoMateria1: EditText ?= null
    var campoMateria2: EditText ?= null
    var campoMateria3: EditText ?= null
    var campoMateria4: EditText ?= null
    var campoMateria5: EditText ?= null
    var campoNota1: EditText ?= null
    var campoNota2: EditText ?= null
    var campoNota3: EditText ?= null
    var campoNota4: EditText ?= null
    var campoNota5: EditText ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        iniciarComponentes()
    }

    private fun iniciarComponentes(){
        campoNombre = findViewById(R.id.nombre)
        campoDocumento = findViewById(R.id.documento)
        campoEdad = findViewById(R.id.edad)
        campoTelefono = findViewById(R.id.telefono)
        campoDireccion = findViewById(R.id.direccion)
        campoMateria1 = findViewById(R.id.materia1)
        campoMateria2 = findViewById(R.id.materia2)
        campoMateria3 = findViewById(R.id.materia3)
        campoMateria4 = findViewById(R.id.materia4)
        campoMateria5 = findViewById(R.id.materia5)
        campoNota1 = findViewById(R.id.nota1)
        campoNota2 = findViewById(R.id.nota2)
        campoNota3 = findViewById(R.id.nota3)
        campoNota4 = findViewById(R.id.nota4)
        campoNota5 = findViewById(R.id.nota5)

        val btnRegistrar: Button =findViewById(R.id.btn_registrar)
        val btnSalir: Button =findViewById(R.id.btn_salir)

        btnRegistrar.setOnClickListener{onClick(1)}
        btnSalir.setOnClickListener{isFinish()}

    }

    private fun isFinish(){
        finish()
    }

    private fun onClick(Boton : Int) {

            try {
                var estu: Estudiante = Estudiante()
                estu.documento = campoDocumento?.text.toString()
                estu.nombre = campoNombre?.text.toString()
                estu.edad = campoEdad?.text.toString().toInt()
                estu.telefono = campoTelefono?.text.toString().toInt()
                estu.direccion = campoDireccion?.text.toString()

                estu.materia1 = campoMateria1?.text.toString()
                estu.materia2 = campoMateria2?.text.toString()
                estu.materia3 = campoMateria3?.text.toString()
                estu.materia4 = campoMateria4?.text.toString()
                estu.materia5 = campoMateria5?.text.toString()

                estu.nota1 = campoNota1?.text.toString().toDouble()
                estu.nota2 = campoNota2?.text.toString().toDouble()
                estu.nota3 = campoNota3?.text.toString().toDouble()
                estu.nota4 = campoNota4?.text.toString().toDouble()
                estu.nota5 = campoNota5?.text.toString().toDouble()

                estu.promedio = Operaciones.calcularPromedio(estu)
                println(estu.promedio)
                Operaciones.imprimirListaEstudiantes()


                if(estu.nota1 >5
                    || estu.nota2>5
                    || estu.nota3>5
                    || estu.nota4>5
                    || estu.nota5>5){
                    Toast.makeText(this, "Las notas no pueden ser mayores a 5", Toast.LENGTH_LONG).show()

                }else if(estu.nota1 <0
                    || estu.nota2<0
                    || estu.nota3<0
                    || estu.nota4<0
                    || estu.nota5<0) {
                    Toast.makeText(this, "Las notas no pueden ser negativos", Toast.LENGTH_LONG).show()

                }else if (estu.nombre.isNullOrBlank()
                    || estu.documento.isNullOrBlank()
                    || estu.direccion.isNullOrBlank()
                    || estu.materia1.isNullOrBlank()
                    || estu.materia2.isNullOrBlank()
                    || estu.materia3.isNullOrBlank()
                    || estu.materia4.isNullOrBlank()
                    || estu.materia5.isNullOrBlank()){
                    Toast.makeText(this, "rellene los campos", Toast.LENGTH_LONG).show()

                } else {
                    Operaciones.registrarEstudiante(estu)

                    var mensajes = ""
                    if (estu.promedio >= 3.5) {
                        mensajes = "Usted ganó el periodo"

                    } else if (estu.promedio > 2.5 && estu.promedio < 3.5) {
                        mensajes = "Usted perdió el periodo pero puede recuperar"

                    }else {
                        mensajes = "Usted perdió el periodo"
                    }
                    estu.resultado=mensajes

                    when (Boton) {
                        1 -> {
                            val intent = Intent(this, ResultadosActivity::class.java)
                            var miBundle: Bundle = Bundle()
                            miBundle.putString("Nombre", estu.nombre)
                            miBundle.putString("Documento", estu.documento)

                            miBundle.putInt("Edad", estu.edad)
                            miBundle.putInt("Telefono", estu.telefono)
                            miBundle.putString("Direccion", estu.direccion)
                            miBundle.putString("Materia1", estu.materia1)
                            miBundle.putString("Materia2", estu.materia2)
                            miBundle.putString("Materia3", estu.materia3)
                            miBundle.putString("Materia4", estu.materia4)
                            miBundle.putString("Materia5", estu.materia5)
                            miBundle.putDouble("Nota1", estu.nota1)
                            miBundle.putDouble("Nota2", estu.nota2)
                            miBundle.putDouble("Nota3", estu.nota3)
                            miBundle.putDouble("Nota4", estu.nota4)
                            miBundle.putDouble("Nota5", estu.nota5)
                            miBundle.putDouble("Promedio", estu.promedio)
                            miBundle.putString("Mensaje", mensajes)

                            intent.putExtras(miBundle)
                            startActivity(intent)

                        }
                    }
                }
            }catch (e:NumberFormatException){
                Toast.makeText(this, "rellene los campos", Toast.LENGTH_LONG).show()
            }
    }


}