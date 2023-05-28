package com.ersafra.horaparada

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ersafra.horaparada.databinding.ActivityTelaDiariasBinding
import com.google.android.gms.ads.*
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


private var mAdViewTC: AdView? = null
private lateinit var binding: ActivityTelaDiariasBinding

class TelaDiarias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaDiariasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        binding.banDiarias.loadAd(adRequest) //banner load

        val rootView = findViewById<View>(android.R.id.content)
        val textInputLayouts = Utils.findViewsWithType(
            rootView, TextInputLayout::class.java
        )

        binding.datainicial.setOnFocusChangeListener { view, isFocused ->
            if (view.isInTouchMode && isFocused) {
                view.performClick()
            }
        }
        binding.datainicial.setOnClickListener {
            showDatePicker()
        }
        binding.btncaldado.setOnClickListener {
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
                calcularDiaria()
            }
        }

    }

    private fun showDatePicker() {
        var c: Calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, yy, mm, dd ->
            var dt = "$dd/${mm + 1}/$yy"
            TimePickerDialog(this, { _, hh, mi ->
                dt += " $hh:$mi"
                binding.datainicial.setText(dt)
            }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true).show()
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun calcularDiaria() {
        //editar quando mudar os valores
        val hantt = 2.12
        val valvha = hantt / 60
        val diadiv = "0,0354"
        //fim da edição
        var dobs = binding.datainicial.text.toString()
        var sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        var dob = sdf.parse(dobs)

        val dataini = dob!!.time / 60000
        val datafim = sdf.parse(sdf.format(System.currentTimeMillis()))
        val datafimT = datafim!!.time / 60000
        val valnhora = datafimT - dataini
//--------------dias e horas------------//
        val data_ini = dob!!.time
        val data_fim = sdf.parse(sdf.format(System.currentTimeMillis()))
        val data_fimT = data_fim!!.time
        val val_nhora = data_fimT - data_ini
        val dias = val_nhora / 86400000
        val horas = val_nhora % 86400000 / 3600000
        val minutos = val_nhora % 86400000 % 3600000 / 60000
        val teste = val_nhora / 60000


        if (valnhora < 300) {
            ("O periodo apurado é de: $valnhora minutos, você só terá direito à indenização a partir de 300 minutos (5 horas).").also {
                binding.dtxt.text = it
            }
            ("").also { binding.dtxt1.text = it }

            return
        }

        val valordahora = valnhora * valvha
        val peso = valordahora.toString()
        val p1: Float = peso.toFloat()
        val diaria1 = binding.capcarga.text.toString()
        val d1: Float = diaria1.toFloat()
        val cmt = p1 * d1
        val dec = DecimalFormat("R$#,##0.00")
        val vcmt = dec.format(cmt)


        ("De acordo com o § 5o do Art. 11 da Lei 11.442/07, alterado pela lei 13.103/2015, o prazo máximo para carga e " +
                "descarga do veículo de Transporte Rodoviário de Cargas será de 5 (cinco) horas, contadas da chegada do veículo ao endereço de destino; após este período será devido ao transportador (carreteiro ou transportadora) o valor de R$ 2,12* " +
                "(dois reais e doze centavos) por tonelada/hora ou fração multiplicado pela capacidade de carga do veículo.\n\n" +
                "Ultrapassado o prazo máximo de 5 horas, o pagamento relativo ao tempo de espera, será calculado a partir " +
                "da hora de chegada na procedência ou no destino.\n\n" +
                "Formula = Cap. x Nºhs x R$2,12 ($diaria1 x $teste x 2,12)\n\n" +
                "* Usamos minutos parados.\n\n" +
                "O valor da diaria é de :\n\n").also { binding.dtxt.text = it }
        (vcmt).also { binding.dtxt1.text = it }

    }

    private fun setupAdsTC() {
        mAdViewTC = findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()
        mAdViewTC?.let { adView ->
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

//    fun voltar(view: View) {
//        startActivity(Intent(this, MainActivity::class.java))
//    }

}


