package com.example.recipeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback

class ViewRecipe : AppCompatActivity() {

    lateinit var mainRV: RecyclerView
    lateinit var addusersBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        mainRV = findViewById(R.id.rvMain)

        addusersBtn = findViewById(R.id.addUsersbtn)
        addusersBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.getRecipe().enqueue(object : Callback<List<RecipeDetailsItem>> {

            override fun onFailure(call: Call<List<RecipeDetailsItem>>, t: Throwable) {
                call.cancel()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<RecipeDetailsItem>>,
                response: retrofit2.Response<List<RecipeDetailsItem>>
            ) {
                val list = response.body()!!
                updateView(list)

            }
        })

    }


    private fun updateView(list: List<RecipeDetailsItem>) {
        mainRV.adapter = RecipeRVAdapter(list)
        mainRV.layoutManager = LinearLayoutManager(this)
    }
}