package com.example.terryschmidt.amiibomania

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class AmiiboDetailActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var series: TextView
    private lateinit var release: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        name = findViewById(R.id.detailName)
        series = findViewById(R.id.detailSeries)
        release = findViewById(R.id.detailRelease)
        name.text = name.text.toString() + intent.extras.get("name").toString()
        series.text = series.text.toString() + intent.extras.get("series").toString()
        release.text = release.text.toString() + intent.extras.get("release").toString()
    }
}