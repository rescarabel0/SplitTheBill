package br.edu.ifsp.ads.pdm.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ads.pdm.splitthebill.databinding.ActivityFormPayerBinding
import br.edu.ifsp.ads.pdm.splitthebill.model.Payer
import br.edu.ifsp.ads.pdm.splitthebill.util.Constants
import kotlin.random.Random

class FormPayerActivity : AppCompatActivity() {
    private val apb: ActivityFormPayerBinding by lazy {
        ActivityFormPayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val currentPayer = intent.getParcelableExtra<Payer>(Constants.CURRENT_PAYER)
        currentPayer?.let { _currentPayer ->
            with(apb) {
                with(_currentPayer) {
                    nameEt.setText(name)
                    paidEt.setText(paid.toString())
                    boughtEt.setText(bought)
                }
            }
        }

        apb.saveBt.setOnClickListener {
            val payer = Payer(
                currentPayer?.id ?: Random(System.currentTimeMillis()).nextInt(),
                apb.nameEt.text.toString(),
                apb.paidEt.text.toString().toDouble(),
                apb.boughtEt.text.toString(),
            )

            val resIntent = Intent()
            setResult(RESULT_OK, resIntent)
            resIntent.putExtra(Constants.PAYER, payer)
            resIntent.putExtra(Constants.ACTION_NAME, Constants.ACTION_ADD)
            finish()
        }
    }
}