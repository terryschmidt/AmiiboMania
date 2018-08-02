package com.example.terryschmidt.amiibomania.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.terryschmidt.amiibomania.AmiiboDetailActivity
import com.example.terryschmidt.amiibomania.R
import com.example.terryschmidt.amiibomania.model.Amiibo

class AmiiboAdapter(var amiibos: List<Amiibo>?) : RecyclerView.Adapter<AmiiboAdapter.AmiiboViewHolder>() {
    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        val amiibo = amiibos?.get(position)
        holder.name.text = amiibo?.name
        holder.amiiboSeries.text = amiibo?.amiiboSeries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.amiibo_item, parent, false)
        return AmiiboViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return amiibos?.size ?: 0
    }

    inner class AmiiboViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(view: View?) {
            Log.d(TAG, "Item pressed: $layoutPosition")
            val detailActivityIntent = Intent(view?.context, AmiiboDetailActivity::class.java)
            detailActivityIntent.putExtra("name", name.text)
            detailActivityIntent.putExtra("series", amiiboSeries.text)
            val release = amiibos?.get(layoutPosition)?.release?.get("na").toString()
            detailActivityIntent.putExtra("release", release)
            view?.context?.startActivity(detailActivityIntent)
        }

        var name: TextView = view.findViewById(R.id.name)
        var amiiboSeries: TextView

        init {
            amiiboSeries = view.findViewById(R.id.amiiboSeries)
            view.setOnClickListener(this)
        }
    }

    fun swap(newAmiibos: List<Amiibo>) {
        amiibos = newAmiibos
        notifyDataSetChanged()
    }

    companion object {
        const val TAG = "AmiiboAdapter"
    }
}