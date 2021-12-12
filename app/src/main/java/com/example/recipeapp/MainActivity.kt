package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var viewuserBtn: Button
    lateinit var addusersbtn: Button

    lateinit var editTextTitle: EditText
    lateinit var editTextAuthor: EditText
    lateinit var editTextIngredients: EditText
    lateinit var editTextInstructions: EditText

    private val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        addusersbtn.setOnClickListener {
            getInfo()
        }

        viewuserBtn.setOnClickListener {
            val intent = Intent(this, ViewRecipe::class.java)
            startActivity(intent)
        }
    }



    private fun init() {
        viewuserBtn = findViewById(R.id.viewBtn)
        addusersbtn = findViewById(R.id.addusserBtn)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextAuthor = findViewById(R.id.editTextAuthor)
        editTextIngredients = findViewById(R.id.editTextIngredients)
        editTextInstructions = findViewById(R.id.editTextInstructions)
    }



    private fun getInfo(){

        val title = editTextTitle.text.toString()
        val author = editTextAuthor.text.toString()
        val ingredients = editTextIngredients.text.toString()
        val instructions = editTextInstructions.text.toString()

        apiInterface!!.addRecipe(RecipeDetailsItem(0,title,author,ingredients,instructions)).enqueue(object:
            Callback<RecipeDetailsItem> {
            override fun onResponse(
                call: Call<RecipeDetailsItem>,
                response: Response<RecipeDetailsItem>
            ) {
                Toast.makeText(applicationContext, "The User Has Been Added Successfully!!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RecipeDetailsItem>, t: Throwable) {
                Toast.makeText(applicationContext, "Sorry,The User Has Not Been Added Successfully!!", Toast.LENGTH_SHORT).show()
            }
        })
    }




}