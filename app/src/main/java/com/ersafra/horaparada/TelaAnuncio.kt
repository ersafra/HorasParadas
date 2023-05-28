package com.ersafra.horaparada

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityTelaAnuncioBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


class TelaAnuncio : AppCompatActivity() {

    private var mAdViewTA: AdView? = null
    private lateinit var binding: ActivityTelaAnuncioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaAnuncioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupAdsTA()
        //btns
        binding.btnWhat.setOnClickListener(this::nTw)
        //binding.btnWhat2.setOnClickListener(this::nTw1)
        //binding.btnWhat3.setOnClickListener(this::nTw2)
        //binding.btnWhat4.setOnClickListener(this::nTw3)
        //binding.btnWhat5.setOnClickListener(this::nTw4)
        //end btns
    }

    //fun btn
    private fun nTw(view: View) {
        try {
            val url =//thiago de campinas
                "https://wa.me//5519982250900?text=Olá,%20Estou%20usando%20o%20aplicativo%20©Hora%20Parada%20e%20estou%20precisando%20de%20seu%20auxilio%20para%20receber%20diarias%20referente%20a%20lei%2013.103/2015."
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
    }/*
    private fun nTw1(view: View) {
        try {
            val url =
                "https://wa.me//5518997785150?text=Olá,%20Estou%20usando%20o%20aplicativo%20©Hora%20Parada%20e%20estou%20precisando%20de%20seu%20auxilio%20para%20receber%20diarias%20referente%20a%20lei%2013.103/2015."
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
    }
    private fun nTw2(view: View) {
        try {
            val url =
                "https://wa.me//5511940076820?text=Olá,%20Estou%20usando%20o%20aplicativo%20©Hora%20Parada%20e%20estou%20precisando%20de%20seu%20auxilio%20para%20receber%20diarias%20referente%20a%20lei%2013.103/2015."
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
    }
    private fun nTw3(view: View) {
        try {
            val url =
                "https://wa.me//5512996707585?text=Olá,%20Estou%20usando%20o%20aplicativo%20©Hora%20Parada%20e%20estou%20precisando%20de%20seu%20auxilio%20para%20receber%20diarias%20referente%20a%20lei%2013.103/2015."
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
    }
    private fun nTw4(view: View) {
        try {
            val url =
                "https://wa.me//5516998058075?text=Olá,%20Estou%20usando%20o%20aplicativo%20©Hora%20Parada%20e%20estou%20precisando%20de%20seu%20auxilio%20para%20receber%20diarias%20referente%20a%20lei%2013.103/2015."
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
    }*/

    //end fun btn
    private fun setupAdsTA() {
        mAdViewTA = findViewById(R.id.adViewTA)

        val adRequest = AdRequest.Builder().build()
        mAdViewTA?.let { adView ->
            adView.loadAd(adRequest)
            setAdModListener(adView)
        }

    }

    private fun setAdModListener(adViewta: AdView) {
        adViewta.adListener = object : AdListener() {
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
}//fim da classe
