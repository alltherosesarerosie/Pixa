package com.geektech.pixa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.geektech.pixa.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())
    var page = 1
    lateinit var list:ArrayList<Hit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            btnEnter.setOnClickListener {
                page = 1
                doRequest()
            }

            imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    ++page
                    doRequest2()
                }
            })
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
                    list = response.body()?.hits!!
                    initAdapter()
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("astra", "onFailure: ${t.message}")
            }
        })
    }


    private fun ActivityMainBinding.doRequest2() {
        PixaService().api.getImages(
            pictureWord = searchEd.text.toString(), page = page
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(
                call: Call<PixaModel>,
                response: Response<PixaModel>
            ) {
                if (response.isSuccessful) {
                        list.addAll(response.body()?.hits!!)
                        initAdapter()
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("astra", "onFailure: ${t.message}")
            }
        })
    }

    private fun ActivityMainBinding.initAdapter() {
        adapter = PixaAdapter(list)
        imageRecycler.adapter = adapter
    }
}