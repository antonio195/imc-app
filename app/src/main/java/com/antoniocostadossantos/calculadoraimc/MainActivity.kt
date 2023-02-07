package com.antoniocostadossantos.calculadoraimc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.calculadoraimc.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            verificarCampos()
        }
    }

    private fun verificarCampos() {
        val altura = binding.etAltura.text.toString()
        val peso = binding.etPeso.text.toString()

        when {
            altura.isEmpty() -> {
                binding.etAltura.error = "Campo obrigatório"
            }
            peso.isEmpty() -> {
                binding.etPeso.error = "Campo obrigatório"
            }
            else -> {
                binding.etAltura.error = null
                binding.etPeso.error = null
                mostrarIMC(calcularIMC())
            }
        }
    }

    private fun calcularIMC(): Double {
        val altura = binding.etAltura.text.toString().toDouble()
        val peso = binding.etPeso.text.toString().toDouble()
        return peso / (altura * altura)
    }

    private fun mostrarIMC(imc: Double) {


        val df = DecimalFormat("##.##")
        df.roundingMode = RoundingMode.DOWN
        val result = df.format(imc).replace(",", ".")

        when {
            imc <= 18.5 -> {
                binding.tvClassificacao.text = "Classificação: Magreza"
                binding.tvResultadoImc.text = "IMC $result"
            }

            imc in 18.5..24.9 -> {
                binding.tvClassificacao.text = "Classificação: Normal"
                binding.tvResultadoImc.text = "IMC $result"
            }

            imc in 25.0..29.9 -> {
                binding.tvClassificacao.text = "Classificação: Sobrepeso"
                binding.tvResultadoImc.text = "IMC $result"
            }

            imc in 30.0..39.9 -> {
                binding.tvClassificacao.text = "Classificação: Obesidade"
                binding.tvResultadoImc.text = "IMC $result"
            }

            imc > 40.0 -> {
                binding.tvClassificacao.text = "Classificação: Obesidade grave"
                binding.tvResultadoImc.text = "IMC $result"
            }
        }
        binding.tvClassificacao.visibility = View.VISIBLE
        binding.tvResultadoImc.visibility = View.VISIBLE
    }

}