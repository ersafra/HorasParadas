package com.ersafra.horaparada

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityTelaCubagemBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.textfield.TextInputLayout

var FatorAerio = 166.7
var FatorRodoviario = 300
var FatorFracionado = 200
var FatorMaritimo = 1000

private var mAdView: AdView? = null
private lateinit var binding: ActivityTelaCubagemBinding
private val MY_SHARED_PREF_NAME = "preferencias"

class TelaCubagem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCubagemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cubagemPref()
//-----admob-----//
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        binding.bannerID.loadAd(adRequest)
//-----admob----//

        val rootView = findViewById<View>(android.R.id.content)
        val textInputLayouts = Utils.findViewsWithType(rootView, TextInputLayout::class.java)

        binding.button2.setOnClickListener { fechar() }
        binding.button.setOnClickListener {
            var noErrors = true
            for (textInputLayout in textInputLayouts) {
                val editTextString = textInputLayout.editText!!.text.toString()
                if (editTextString.isEmpty()) {
                    textInputLayout.error = resources.getString(R.string.error_string)
                    noErrors = false
                } else {
                    textInputLayout.error = null
                }
            }
            if (noErrors) {
                calcular()
            }
        }

    }

    private fun calcular() {
        val u = binding.quantidade.text.toString()
        val und = u.toDouble()
        val c = binding.comprimento.text.toString()
        val comp = c.toDouble()
        val a = binding.altura.text.toString()
        val alt = a.toDouble()
        val l = binding.largura.text.toString()
        val lar = l.toDouble()

        val res = comp * alt * lar * und
        val rp2 = res
        val rp3 = String.format("%.2f", rp2)

        val resr = res * FatorRodoviario
        val resrf = String.format("%.2f", resr)

        val resa = res * FatorAerio
        val resaf = String.format("%.2f", resa)

        val resm = res * FatorMaritimo
        val resmf = String.format("%.2f", resm)

        val resfr = res * FatorFracionado
        val resfra = String.format("%.2f", resfr)

        val las = comp * lar
        val lastro = String.format("%.2f", las)

        ("Metragem no Baú: $rp3 m3\n\n" +
                "Peso Cubado Rodoviario: $resrf kg\n\n" +
                "Peso Cubado Fracionado: $resfra kg\n\n" +
                "Metragem no Lastro : $lastro m2").also { binding.bau.text = it }
    }

    private fun cubagemPref() {
        val sharePref = this.getSharedPreferences(
            MY_SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        binding.comprimento.setText(sharePref.getString("COMKEY", ""))
        binding.altura.setText(sharePref.getString("ALTKEY", ""))
        binding.largura.setText(sharePref.getString("LARKEY", ""))
    }

    private fun fechar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
