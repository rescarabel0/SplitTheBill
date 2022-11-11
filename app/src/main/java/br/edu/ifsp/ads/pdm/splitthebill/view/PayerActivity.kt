package br.edu.ifsp.ads.pdm.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ads.pdm.splitthebill.databinding.ActivityPayerBinding
import br.edu.ifsp.ads.pdm.splitthebill.model.Payer
import br.edu.ifsp.ads.pdm.splitthebill.util.Constants

class PayerActivity : AppCompatActivity() {
    private val apb: ActivityPayerBinding by lazy {
        ActivityPayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val currentPayer = intent.getParcelableExtra<Payer>(Constants.CURRENT_PAYER)

        apb.payerNameTv.text = currentPayer?.name
        apb.payerPaidTv.text = "Paid: $${currentPayer?.paid}"
        apb.payerBoughtTv.text = "Bought: ${currentPayer?.bought}"

        apb.deleteBtn.setOnClickListener {
            val resIntent = Intent()
            setResult(RESULT_OK, resIntent)
            resIntent.putExtra(Constants.ACTION_NAME, Constants.ACTION_DELETE)
            resIntent.putExtra(Constants.PAYER, currentPayer)
            finish()
        }

        apb.editBtn.setOnClickListener {
            val resIntent = Intent()
            setResult(RESULT_OK, resIntent)
            resIntent.putExtra(Constants.ACTION_NAME, Constants.ACTION_EDIT)
            resIntent.putExtra(Constants.PAYER, currentPayer)
            finish()
        }
    }
}