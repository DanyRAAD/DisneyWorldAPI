package com.dani.disneyworldapi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dani.disneyworldapi.R
import com.dani.disneyworldapi.data.remote.DisneyApi
import com.dani.disneyworldapi.data.remote.model.Data
import com.dani.disneyworldapi.data.remote.model.DataDetail
import com.dani.disneyworldapi.databinding.FragmentCharacterListBinding
import com.dani.disneyworldapi.util.Constants
import com.dani.disneyworldapi.view.adapters.CharactersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class CharacterListFragment : Fragment() {
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val disneyApi = retrofit.create(DisneyApi::class.java)

        val call: Call<MutableList<Data>> = disneyApi.getDataApiary()

        call.enqueue(object: Callback<MutableList<Data>>{
            override fun onResponse(p0: Call<MutableList<Data>>, response: Response<MutableList<Data>>) {

                binding.pbLoading.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, response.toString())
                Log.d(Constants.LOGTAG, response.body().toString())

                binding.apply {
                    rvData.layoutManager = LinearLayoutManager(requireActivity())
                    rvData.adapter = CharactersAdapter(response.body()!!){data ->
                        //Manejo de click
                        data.id?.let{ id ->
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, CharacterDetailFragment.newInstance(id))
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }

            }

            override fun onFailure(p0: Call<MutableList<Data>>, p1: Throwable) {

                binding.pbLoading.visibility = View.INVISIBLE

                //Manejar el error de conexion, añadir algo para cuando se reestablesca la conexion
                Toast.makeText(
                    requireActivity(),
                    "No hay conexion disponible",
                    Toast.LENGTH_SHORT
                ).show()

                p1.message
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}