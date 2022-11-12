package br.edu.ifsp.ads.pdm.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.ads.pdm.splitthebill.R
import br.edu.ifsp.ads.pdm.splitthebill.adapter.PayerAdapter
import br.edu.ifsp.ads.pdm.splitthebill.databinding.ActivityMainBinding
import br.edu.ifsp.ads.pdm.splitthebill.model.Payer
import br.edu.ifsp.ads.pdm.splitthebill.util.Constants

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val payersList: MutableList<Payer> = mutableListOf()

    private lateinit var payerAdapter: PayerAdapter

    private lateinit var parl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        fillPayerList()
        payerAdapter = PayerAdapter(this, payersList)
        amb.mainLv.adapter = payerAdapter

        parl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { res ->
            if (res.resultCode == RESULT_OK) {
                val actionName = res.data?.getStringExtra(Constants.ACTION_NAME)

                if (actionName == Constants.ACTION_CALCULATE) {
                    val total = res.data?.getDoubleExtra(Constants.TOTAL, 0.0)
                    total?.let { _total ->
                        val valueForEachPayer = _total / payersList.size
                        payersList.forEach {
                            it.balance = -(it.paid - valueForEachPayer)
                        }
                    }
                }

                val payer = res.data?.getParcelableExtra<Payer>(Constants.PAYER)
                payer?.let { _payer ->
                    when (actionName) {
                        Constants.ACTION_ADD -> {
                            val position = payersList.indexOfFirst { it.id == _payer.id }
                            if (position != -1)
                                payersList[position] = _payer
                            else payersList.add(_payer)
                        }
                        Constants.ACTION_DELETE -> {
                            payersList.remove(_payer)
                        }
                        Constants.ACTION_EDIT -> {
                            val formIntent = Intent(this, FormPayerActivity::class.java)
                            formIntent.putExtra(Constants.CURRENT_PAYER, _payer)
                            parl.launch(formIntent)
                        }
                        else -> null
                    }
                }
                payerAdapter.notifyDataSetChanged()
            }
        }

        amb.mainLv.setOnItemClickListener { _, _, position, _ ->
            val payerIntent = Intent(this, PayerActivity::class.java)
            payerIntent.putExtra(Constants.CURRENT_PAYER, payersList[position])
            parl.launch(payerIntent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_payer_mi -> {
                parl.launch(Intent(this, FormPayerActivity::class.java))
                true
            }
            R.id.calculate_mi -> {
                parl.launch(Intent(this, CalculateExpensesActivity::class.java))
                true
            }
            else -> false
        }
    }

    private fun fillPayerList() {
        for (i in 1..5) {
            payersList.add(
                Payer(
                    i,
                    "Nome $i",
                    i.toDouble(),
                    "Comprou $i",
                    null
                )
            )
        }
    }
}