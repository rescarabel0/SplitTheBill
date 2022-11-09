package br.edu.ifsp.ads.pdm.splitthebill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.ads.pdm.splitthebill.databinding.ActivityPayerBinding

class PayerActivity : AppCompatActivity() {
    private val apb: ActivityPayerBinding by lazy {
        ActivityPayerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)
    }
}