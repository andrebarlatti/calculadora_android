package com.andrebarlatti.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrebarlatti.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculo = binding.calculo

        // Mapear botões a seus respectivos valores para evitar repetição
        val buttonsMap = mapOf(
            binding.um to "1",
            binding.dois to "2",
            binding.tres to "3",
            binding.quatro to "4",
            binding.cinco to "5",
            binding.seis to "6",
            binding.sete to "7",
            binding.oito to "8",
            binding.nove to "9",
            binding.zero to "0",
            binding.ponto to ".",
            binding.parenteseAbrindo to "(",
            binding.parenteseFechando to ")",
            binding.divisao to "/",
            binding.multiplicacao to "*",
            binding.menos to "-",
            binding.mais to "+"
        )

        buttonsMap.forEach { (button, value) ->
            button.setOnClickListener {
                calculo.text = "${calculo.text}$value"
            }
        }

        // Configurar ações específicas para botões especiais
        binding.apagar.setOnClickListener {
            calculo.text = calculo.text.dropLast(1)
        }

        binding.ce.setOnClickListener {
            calculo.text = ""
            binding.resultado.text = ""
        }

        binding.igual.setOnClickListener {
            val resultadoCalculado = Expression(calculo.text.toString()).calculate()

            if (resultadoCalculado.isNaN()) {
                binding.resultado.text = "Expressão inválida"
            } else {
                binding.resultado.text = resultadoCalculado.toString()
            }
        }
    }
}
