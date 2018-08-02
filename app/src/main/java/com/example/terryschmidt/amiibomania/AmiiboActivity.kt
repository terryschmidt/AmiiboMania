package com.example.terryschmidt.amiibomania

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.terryschmidt.amiibomania.adapter.AmiiboAdapter
import com.example.terryschmidt.amiibomania.model.Amiibo
import com.example.terryschmidt.amiibomania.model.AmiiboListWrapper
import com.example.terryschmidt.amiibomania.network.GetDataService
import com.example.terryschmidt.amiibomania.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AmiiboActivity : AppCompatActivity() {

    private lateinit var amiiboRecycler: RecyclerView
    private lateinit var amiibos: List<Amiibo>
    private lateinit var amiiboAdapter: AmiiboAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amiibo)
        amiiboRecycler = findViewById(R.id.amiiboRecycler)
        amiibos = ArrayList()
        amiiboAdapter = AmiiboAdapter(amiibos)
        val layoutManager = LinearLayoutManager(this)
        amiiboRecycler.setLayoutManager(layoutManager)
        amiiboRecycler.adapter = amiiboAdapter
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        amiiboRecycler.addItemDecoration(dividerItemDecoration)

        /*Create handle for the RetrofitInstance interface*/
        val service = RetrofitInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        val call = service?.getAmiiboListWrapper()
        call?.enqueue(object : Callback<AmiiboListWrapper> {
            override fun onFailure(call: Call<AmiiboListWrapper>?, t: Throwable?) {
                Log.e(TAG, "onFailure: " + t?.message)
            }

            override fun onResponse(call: Call<AmiiboListWrapper>?, response: Response<AmiiboListWrapper>?) {
                Log.d(TAG, "onResponse: " + response?.isSuccessful)

                if (response == null) { Log.e(TAG, "null response") }
                if (response!!.body() == null) { Log.e(TAG, "null body") }
                if (response!!.body()!!.amiiboList == null) { Log.e(TAG, "null amiiboList") }

                var newAmiiboList: List<Amiibo>? = response.body()?.amiiboList
                if (newAmiiboList != null) {
                    amiiboAdapter.swap(newAmiiboList)
                }

                Log.d("TerryLog", "NA Release: " + newAmiiboList?.get(0)?.release?.get("na"))
            }
        })
    }

    companion object {
        const val TAG = "AmiiboActivity"
    }
}