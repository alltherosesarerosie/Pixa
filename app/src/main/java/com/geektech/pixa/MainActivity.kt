package com.geektech.pixa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.geektech.pixa.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            btnChangePage.setOnClickListener {
                ++page
                doRequest()
            }
            btnEnter.setOnClickListener {
                page = 1
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        PixaService().api.getImages(
            pictureWord = searchEd.text.toString(), page = page
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(
                call: Call<PixaModel>,
                response: Response<PixaModel>
            ) {
                if (response.isSuccessful) {
                    adapter = PixaAdapter(response.body()?.hits!!)
                    imageRecycler.adapter = adapter
                    Log.e("astra", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("astra", "onFailure: ${t.message}")
            }
        })
    }
}