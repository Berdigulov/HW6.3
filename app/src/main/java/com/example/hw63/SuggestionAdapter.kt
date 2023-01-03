package com.example.hw63

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw63.databinding.ItemSuggestionBinding

class SuggestionAdapter(
    private val list: ArrayList<String>,
    private val onClick: (String) -> Unit
    ):RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        return SuggestionViewHolder(ItemSuggestionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class SuggestionViewHolder(private var binding: ItemSuggestionBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(text: String){
            binding.tvSharps.text = text
            itemView.setOnClickListener {
                onClick(text)
            }
        }
    }
}