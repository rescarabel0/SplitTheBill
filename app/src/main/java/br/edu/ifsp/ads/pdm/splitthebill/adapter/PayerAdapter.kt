package br.edu.ifsp.ads.pdm.splitthebill.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.ads.pdm.splitthebill.R
import br.edu.ifsp.ads.pdm.splitthebill.model.Payer

class PayerAdapter(
    context: Context,
    private val payerList: MutableList<Payer>
) : ArrayAdapter<Payer>(context, R.layout.tile_payer, payerList) {
    private data class TilePayerHolder(val nameTv: TextView, val paidTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val payer = payerList[position]
        var payerTileView = convertView

        if (payerTileView == null) {
            payerTileView =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_payer,
                    parent,
                    false
                )
            val tilePayerHolder = TilePayerHolder(
                payerTileView.findViewById(R.id.name_tv),
                payerTileView.findViewById(R.id.paid_tv),
            )
            payerTileView.tag = tilePayerHolder
        }

        with(payerTileView?.tag as TilePayerHolder) {
            nameTv.text = payer.name
            paidTv.text = "Paid: $" + payer.paid.toString()
        }

        return payerTileView
    }
}