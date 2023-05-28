package com.ersafra.horaparada

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class MainActivity : AppCompatActivity() {

    private var mAdView: AdView? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAds()
        initclicks()

    }


    fun initclicks() {
        binding.btnCalculo.setOnClickListener(this::abreTelaDiarias)
        binding.btnCubagem.setOnClickListener(this::abreTelaCubagem)
        binding.btnInss.setOnClickListener(this::abreTelaInss)
        binding.btnDados1.setOnClickListener(this::abreTelaDados)

    }

    fun abreTelaDiarias(view: View) {
        startActivity(Intent(this, TelaDiarias::class.java))
    }

    fun abreTelaCubagem(view: View) {
        startActivity(Intent(this, TelaCubagem::class.java))
    }

    //
    fun abreTelaInss(view: View) {
        startActivity(Intent(this, TelaInss::class.java))
    }


    fun abreTelaDados(view: View) {
        startActivity(Intent(this, MeusDados::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.qufa -> {
                val build = AlertDialog.Builder(this)
                val view = layoutInflater.inflate(R.layout.about, null)
                build.setView(view)

                val btnClose = view.findViewById<Button>(R.id.btnclose)
                btnClose.setOnClickListener { dialog.dismiss() }

                val btnZap = view.findViewById<ImageButton>(R.id.btnabzap)
                btnZap.setOnClickListener { abrezap(view) }

                dialog = build.create()
                dialog.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun abrezap(view: View) {
        try {
            val url = "https://wa.me//5519989380032?text=Olá%20Safra!"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp"))
        } catch (e: Exception) {/*todo*/
        }
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

