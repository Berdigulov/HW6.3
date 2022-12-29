package com.example.hw63

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.hw63.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listHashtag = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        click()
    }

    private fun click(){
        binding.btnPut.setOnClickListener {
            val text = binding.atvInput.text.split(" ")
            listHashtag.add(text.toString())
            listHashtag.forEach { item ->
                if (!item.startsWith("#")) {
                    listHashtag.add(item)
                    ArrayAdapter(this,android.R.layout.simple_list_item_1,listHashtag).also { adapter ->
                        binding.atvInput.setAdapter(adapter)
                    }
                    Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "YOU SHOULD WRITE #", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}