package com.example.hw63

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.hw63.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listHashtag = ArrayList<String>()
    private val adapter = SuggestionAdapter(listHashtag,this::onClick)
    private var oldWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        click()
        initListener()
    }

    private fun initListener() {
        binding.etInput.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val words = text.toString().replace(",", " ").split(" ")

                for (word in words){
                    if(word.startsWith("#")){
                        oldWord = word
                    }
                    binding.recycler.isVisible = word.startsWith("#")
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun onClick(sharp: String) {
        binding.recycler.isVisible = false
        binding.etInput.setText(binding.etInput.text.toString().replace(oldWord,sharp))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun click(){
        binding.btnPut.setOnClickListener {
            if(binding.etInput.text.isNotEmpty()){
                val suggestions = binding.etInput.text.toString().replace(",", " ")
                val words: List<String> = suggestions.split(" ")

                for (word in words){
                    if(word.startsWith("#")){
                        listHashtag.add(word)
                    }
                }

                binding.etInput.text.clear()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this,"Your input in empty",Toast.LENGTH_SHORT).show()
            }
        }
    }
}