package com.dani.disneyworldapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dani.disneyworldapi.R
import com.dani.disneyworldapi.data.remote.DisneyApi
import com.dani.disneyworldapi.data.remote.model.DataDetail
import com.dani.disneyworldapi.databinding.FragmentCharacterDetailBinding
import com.dani.disneyworldapi.util.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_ID = "id"



class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private var id: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val disneyApi = retrofit.create(DisneyApi::class.java)

        val call = disneyApi.getDataDetailApiary(id)

        call.enqueue(object: Callback<DataDetail>{
            override fun onResponse(p0: Call<DataDetail>, response: Response<DataDetail>) {
                binding.apply {
                    tvTitle.text = response.body()?.name
                    //tvLongDesc.text = response.body()?.films

                    Picasso.get()
                        .load(response.body()?.imageUrl)
                        .into(binding.ivImage)

                }

            }

            override fun onFailure(p0: Call<DataDetail>, p1: Throwable) {
                //manejamos el error
            }

        })

    }

    companion object {

        @JvmStatic
        fun newInstance(id: String) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                }
            }
    }
}