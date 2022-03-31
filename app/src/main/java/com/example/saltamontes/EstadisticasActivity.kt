package com.example.saltamontes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.saltamontes.clases.Operaciones



class EstadisticasActivity : AppCompatActivity() {

    var campoCantiProcesados: TextView ?= null
    var campoCantiGanan: TextView ?= null
    var campoCantiPierden: TextView ?= null
    var campoCantiRecuperan: TextView ?= null

    var listaEstu = Operaciones.listaEstudiantes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)
        iniciarComponentes()

        var btnSalir: Button = findViewById(R.id.btn_salirEst)
        btnSalir.setOnClickListener{salir()}

    }

    private fun iniciarComponentes(){

        var cantidadTotal=listaEstu.size
        var cantidadGanados:Int=totalGanadores()
        var cantidadPerdedores:Int=totalPerdedores()
        var cantidadRecuperacion:Int=totalRecuperacion()

        campoCantiProcesados = findViewById(R.id.textCantProcesados)
        campoCantiGanan = findViewById(R.id.textCantGanan)
        campoCantiPierden = findViewById(R.id.textCantPierden)
        campoCantiRecuperan = findViewById(R.id.textCantRecuperan)

        campoCantiProcesados?.text = "$cantidadTotal"
        campoCantiGanan?.text = "$cantidadGanados"
        campoCantiPierden?.text = "$cantidadPerdedores"
        campoCantiRecuperan?.text = "$cantidadRecuperacion"
    }

    private fun totalRecuperacion(): Int {
        var cantidad = 0
        for (est in listaEstu){
            if (est.resultado.equals("Usted perdió el periodo pero puede recuperar")){
                cantidad =+ 1
            }
        }
        return cantidad
    }

    private fun totalPerdedores(): Int {
        var cantidad = 0
        for (est in listaEstu){
            if (est.resultado.equals("Usted perdió el periodo")){
                cantidad =+ 1
            }
        }
        return cantidad
    }

    private fun totalGanadores(): Int {
        var cantidad = 0
        for (est in listaEstu){
            if (est.resultado.equals("Usted ganó el periodo")){
                cantidad =+ 1
            }
        }
        return cantidad
    }


    private fun salir(){
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}