package com.example.azantime.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.azantime.R
import com.example.azantime.databinding.MainFragment2Binding
import com.example.azantime.model.Item
import com.example.azantime.model.State
import com.example.azantime.model.salatRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class main_fragment2 : Fragment(R.layout.main_fragment2) {
    lateinit var binding: MainFragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragment2Binding.inflate(inflater, container, false)

        val city = arguments?.getString("CITY").toString()
        getSalat(city)

        binding.backBtn.setOnClickListener {
            replaceFragment(search_fragment1())
        }


        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {


        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.commit()
    }

    private fun getSalat(city: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            salatRepo().getSalat(city).collect {
                when (it) {
                    is State.Fail -> {}
                    State.Loading -> {}
                    is State.Success -> viewOnUI(it.data.items.first())
                }
            }
        }
    }

    private fun viewOnUI(salat: Item) {
        //Edit text in ui from API
        binding.apply {
            alfajrTimeTv.text = salat.fajr
            alzuhrTimeTv.text = salat.dhuhr
            aleasrTimeTv.text = salat.asr
            almaghribTimeTv.text = salat.maghrib
            aleishaTimeTv.text = salat.isha

        }
    }


}
