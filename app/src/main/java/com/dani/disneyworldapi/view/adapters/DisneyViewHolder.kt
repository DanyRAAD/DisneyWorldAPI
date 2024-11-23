package com.dani.disneyworldapi.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dani.disneyworldapi.data.remote.model.Data
import com.dani.disneyworldapi.databinding.CharacterElementBinding

class DisneyViewHolder (
  private val binding: CharacterElementBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Data){


        binding.tvFullName.text = character.name
       binding.txcreationDate.text = character.createdAt
        //imagen
        Glide.with(binding.root.context)
            .load(character.imageUrl)
            .into(binding.ivIcono)
    }

}