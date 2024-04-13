package com.ridhamsharma.apiintegrationapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ridhamsharma.apiintegrationapplication.databinding.ActivityMainBinding
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var restInterface : RestInterface?= null
    var retrofit : Retrofit? = null
    private  val TAG = "MainActivity"
    lateinit var arrayAdapter : ArrayAdapter<GetResponseItem>
    var list = arrayListOf<GetResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.list.adapter = arrayAdapter
        retrofit = Retrofit.Builder().baseUrl("https://gorest.co.in/public/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        restInterface = retrofit?.create(RestInterface::class.java)

        restInterface?.getUsers()?.enqueue(object: Callback<GetResponse>{
            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                if(response.isSuccessful){
                    Log.e(TAG, "in response ${response.body()}")
                    var apiResponse = response.body() as ArrayList<GetResponseItem>
                    list.addAll(apiResponse)
                    arrayAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {

            }
        })

        binding.fab.setOnClickListener {
            restInterface?.addUser("Bearer 6d08188f6d09636fe92310538ff0b03a4bcd6d025ae33b7d10ad1daabf7b24ee",
                "Testing name", "testing8754@gmail.com","female", "active")
                ?.enqueue(object: Callback<GetResponseItem>{
                    override fun onResponse(
                        call: Call<GetResponseItem>,
                        response: Response<GetResponseItem>
                    ) {
                        Log.e(TAG, " in response ${response.body()}")
                        Log.e(TAG, " in response error ${response.errorBody()}")
                        if(response.isSuccessful)
                            Toast.makeText(this@MainActivity, "User added successfully", Toast.LENGTH_LONG).show()
                        else{
                            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()

                        }
                    }

                    override fun onFailure(call: Call<GetResponseItem>, t: Throwable) {
                        Log.e(TAG,"in failure ${t.message}")
                    }
                })
        }

    }
}