package br.edu.ifsp.ads.pdm.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ads.pdm.splitthebill.databinding.ActivityCalculateExpensesBinding
import br.edu.ifsp.ads.pdm.splitthebill.util.Constants

class CalculateExpensesActivity : AppCompatActivity() {
    private val acb: ActivityCalculateExpensesBinding by lazy {
        ActivityCalculateExpensesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        acb.calcBt.setOnClickListener {
            val total = acb.totalEt.text.toString().toDouble()

            val resIntent = Intent()
            setResult(RESULT_OK, resIntent)
            resIntent.putExtra(Constants.ACTION_NAME, Constants.ACTION_CALCULATE)
            resIntent.putExtra(Constants.TOTAL, total)
            finish()
        }
    }
}