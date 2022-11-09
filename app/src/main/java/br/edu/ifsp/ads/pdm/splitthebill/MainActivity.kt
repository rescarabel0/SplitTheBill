package br.edu.ifsp.ads.pdm.splitthebill

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
                val payer = res.data?.getParcelableExtra<Payer>(Constants.NEW_PAYER)

                payer?.let { _payer ->
                    val position = payersList.indexOfFirst { it.id == _payer.id }
                    if (position != -1)
                        payersList[position] = _payer
                    else payersList.add(_payer)

                    payerAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun fillPayerList() {
        for (i in 1..5) {
            payersList.add(
                Payer(
                    i,
                    "Nome $i",
                    i.toDouble(),
                    "Comprou $i"
                )
            )
        }
    }
}