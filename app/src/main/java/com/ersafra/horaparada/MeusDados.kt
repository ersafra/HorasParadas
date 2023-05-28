package com.ersafra.horaparada

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityMeusDadosBinding

class MeusDados : AppCompatActivity() {

    private lateinit var binding: ActivityMeusDadosBinding
    private val MY_SHARED_PREF_NAME = "preferencias"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeusDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dadosSalvos()
        binding.btnSalvar.setOnClickListener {
            salvarDados()
        }
    }

    private fun salvarDados() {

        val sharePref = getSharedPreferences(
            MY_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )

        val editor = sharePref.edit()
        editor.putString("TONKEY", binding.tonkey.text.toString())
        editor.putString("PALKEY", binding.palkey.text.toString())
        editor.putString("ALTKEY", binding.altkey.text.toString())
        editor.putString("COMKEY", binding.comkey.text.toString())
        editor.putString("LARKEY", binding.larkey.text.toString())
        editor.putString("EIXKEY", binding.eixkey.text.toString())
        editor.apply()

        Toast.makeText(this, "Dados Salvos", Toast.LENGTH_SHORT).show()

    }

    private fun dadosSalvos() {

        val sharePref = this.getSharedPreferences(
            MY_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )

        val key_ton = sharePref.getString("TONKEY", "")
        val key_pal = sharePref.getString("PALKEY", "")
        val key_alt = sharePref.getString("ALTKEY", "")
        val key_com = sharePref.getString("COMKEY", "")
        val key_lar = sharePref.getString("LARKEY", "")
        val key_eix = sharePref.getString("EIXKEY", "")

        ("Eixos:$key_eix\n" +
                "Peso Bruto: $key_ton\n" +
                "Paletes: $key_pal\n" +
                "Comprimento: $key_com\n" +
                "Altura: $key_alt\n" +
                "Largura: $key_lar").also { binding.dadosResult.text = it }

    }
}

