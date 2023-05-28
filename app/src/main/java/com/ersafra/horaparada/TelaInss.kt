package com.ersafra.horaparada

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityTelaInssBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.material.textfield.TextInputLayout

var FINSS = 20
var FIRRF = 10 / 100
var FSEST = 1.5 / 100
var FSENAT = 1 / 100
var FISS = 5 / 100

class TelaInss : AppCompatActivity() {

    private var mAdView: AdView? = null
    private lateinit var binding: ActivityTelaInssBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaInssBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupAds()

        val rootView = findViewById<View>(android.R.id.content)
        val textInputLayouts = Utils.findViewsWithType(
            rootView, TextInputLayout::class.java
        )

        binding.btncalcinss.setOnClickListener {
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
        val base = binding.basecalc.text.toString()
        val basecalc = base.toDouble()
        val inss20 = basecalc * 20 / 100
        val inss11 = inss20 * 11 / 100
        val inssfim = String.format("%.2f", inss11)
//-----------------------------------------------//
        val irrf = basecalc * 10 / 100
        val irrfim = String.format("%.2f", irrf)
//-----------------------------------------------//
        val sest = inss20 * 1.5 / 100
        val sestfim = String.format("%.2f", sest)
//-----------------------------------------------//
        val senat = inss20 * 1 / 100
        val senatfim = String.format("%.2f", senat)
//----------------------------------------------//
        val iss = basecalc * 5 / 100
        val issfim = String.format("%.2f", iss)
//--------------------------------------------//
        val a = basecalc.toDouble()
        val b = inss11.toDouble()
        val c = irrf.toDouble()
        val d = sest.toDouble()
        val e = senat.toDouble()
        val f = iss.toDouble()
        val t = a - b - d - e - f
        //val total = String.format("%.2f",t)
//--------------------------------------------//
        val tota = (inss11 + sest + senat)
        val total = String.format("%.2f", tota)
//----------------------------------------//
        ("INSS:  (Base x 20% x 11%) :  R$ $inssfim\n" +
                "SEST:  (Base x 20% x 1%) : R$ $sestfim\n" +
                "SENAT: (Base x 20% x 1,5%) : R$ $senatfim\n\n" +
                "TOTAL ( inss+sest+senat ) : R$ $total\n\n" +
                "IRRF:  (Base x 10%) : R$ $irrfim *\n" +
                "ISS:   (Base x 5%) : R$ $issfim **\n"
                ).also { binding.itxt.text = it }

    }

    private fun setupAds() {
        mAdView = findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()
        mAdView?.let { adView ->
            adView.loadAd(adRequest)
            setAdModListener(adView)
        }

    }

    private fun setAdModListener(adView: AdView) {
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.e("======> ", "onAdLoaded: ")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e("======> ", "onAdFailedToLoad: $adError ")
            }

            override fun onAdOpened() {
                Log.e("======> ", "onAdOpened")
            }

            override fun onAdClicked() {
                Log.e("======> ", "onAdClicked")
            }

            override fun onAdClosed() {
                Log.e("======> ", "onAdClosed")
            }
        }
    }


}

