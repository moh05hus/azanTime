package com.example.azantime.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.azantime.R
import com.example.azantime.UI.main_fragment2
import com.example.azantime.databinding.SearchFragment1Binding

class search_fragment1: Fragment(R.layout.search_fragment1) {
    lateinit var binding: SearchFragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragment1Binding.inflate(inflater,container,false)

        binding.searchCityBtn.setOnClickListener{
        replaceFragment(main_fragment2())
        }




        return binding.root
    }
    private fun replaceFragment (fragment : Fragment){

         val bundle = Bundle()
        bundle.putString("CITY",binding.searchCity.text.trim().toString())
        fragment.arguments = bundle

            val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,fragment)
        fragmentTransaction?.commit()
    }



}