package com.dani.disneyworldapi.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.disneyworldapi.data.remote.model.Data
import com.dani.disneyworldapi.databinding.CharacterElementBinding

class CharactersAdapter(
    private  val data: MutableList<Data>,
    private val onDataClick: (Data) -> Unit
): RecyclerView.Adapter<DisneyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return DisneyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: DisneyViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
        holder.itemView.setOnClickListener{
            onDataClick(data)
        }

    }
}