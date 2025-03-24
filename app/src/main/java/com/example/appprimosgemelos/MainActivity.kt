package com.example.appprimosgemelos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appprimosgemelos.databinding.ActivityMainBinding;
class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnResultado.setOnClickListener {
            verificarPrimosGemelos()
            binding.etNumero1.setText("")
            binding.etNumero2.setText("")
        }
    }

    private fun verificarPrimosGemelos() {
        val num1 = binding.etNumero1.text.toString().toIntOrNull()
        val num2 = binding.etNumero2.text.toString().toIntOrNull()

        if (num1 == null || num2 == null) {
            mostrarMensaje("Ingrese números válidos")
            return
        }

        if (esPrimo(num1) && esPrimo(num2) && kotlin.math.abs(num1 - num2) == 2) {
            binding.tvResultado.text = "¡Son primos gemelos! ✅"
        } else {
            binding.tvResultado.text = "No son primos gemelos ❌ "
        }
    }

    private fun esPrimo(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2 until n) {
            if (n % i == 0) return false
        }
        return true
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}