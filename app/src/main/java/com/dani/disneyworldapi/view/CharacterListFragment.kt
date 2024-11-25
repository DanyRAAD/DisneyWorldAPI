package com.dani.disneyworldapi.view

import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dani.disneyworldapi.R
import com.dani.disneyworldapi.data.remote.DisneyApi
import com.dani.disneyworldapi.data.remote.NetworkChangeReceiver
import com.dani.disneyworldapi.data.remote.model.Data
import com.dani.disneyworldapi.databinding.FragmentCharacterListBinding
import com.dani.disneyworldapi.util.Constants
import com.dani.disneyworldapi.view.adapters.CharactersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.net.ConnectivityManager



class CharacterListFragment : Fragment() {
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Registrar el receptor de cambios en la red
        networkChangeReceiver = NetworkChangeReceiver {
            // Llama al método que carga los datos cuando se detecta conexión
            fetchData()
        }

        requireActivity().registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        // Intenta cargar los datos al iniciar
        fetchData()

    }


    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(networkChangeReceiver)
    }

    private fun fetchData() {
        binding.pbLoading.visibility = View.VISIBLE
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val disneyApi = retrofit.create(DisneyApi::class.java)
        val call: Call<MutableList<Data>> = disneyApi.getDataApiary()

        call.enqueue(object : Callback<MutableList<Data>> {
            override fun onResponse(call: Call<MutableList<Data>>, response: Response<MutableList<Data>>) {
                binding.pbLoading.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val dataList = response.body()
                    if (dataList != null && dataList.isNotEmpty()) {
                        binding.rvData.apply {
                            layoutManager = LinearLayoutManager(requireActivity())
                            adapter = CharactersAdapter(dataList) { data ->
                                data.id?.let { id ->
                                    requireActivity().supportFragmentManager.beginTransaction()
                                        .replace(R.id.fragment_container, CharacterDetailFragment.newInstance(id))
                                        .addToBackStack(null)
                                        .commit()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(requireActivity(), "No se encontraron datos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Error del servidor: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MutableList<Data>>, t: Throwable) {
                binding.pbLoading.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Sin conexión a Internet. Esperando reconexión...", Toast.LENGTH_LONG).show()
            }
        })
    }


}