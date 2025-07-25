package com.example.nasaapiapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import com.example.nasaapiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var APODList: MutableList<org.json.JSONObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apodList.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        APODList = mutableListOf()

        fetchAPODImage()
    }

    private fun fetchAPODImage() {
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = "KEY"
        params["count"] = "5"

        client["https://api.nasa.gov/planetary/apod", params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val jsonArray = json.jsonArray
                for (i in 0 until jsonArray.length()) {
                    jsonArray.getJSONObject(i)
                    APODList.add(jsonArray.getJSONObject(i))
                }
                val adapter = APODAdapter(APODList)
                binding.apodList.adapter = adapter
                binding.apodList.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("APOD Error", errorResponse)
            }
        }]
    }
}