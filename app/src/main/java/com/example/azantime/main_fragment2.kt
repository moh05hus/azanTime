package com.example.azantime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.azantime.databinding.MainFragment2Binding
import com.example.azantime.model.Item

class main_fragment2 : Fragment(R.layout.main_fragment2), ISalatView {
    lateinit var binding: MainFragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragment2Binding.inflate(inflater, container, false)

        SalatPresenter(requireActivity()).getDataFromAPI("jeddah")

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


    override fun onDataCompleteFromAPI(salat: Item) {
        //Edit text in ui from API
        binding.apply {
            alfajrTimeTv.text = salat.fajr
            alzuhrTimeTv.text = salat.dhuhr
            aleasrTimeTv.text = salat.asr
            almaghribTimeTv.text = salat.maghrib
            aleishaTimeTv.text = salat.isha

        }
    }

    override fun onDataErrorFromApi(throwabal: Throwable) {

        Toast.makeText(context, "Error -----> ${throwabal.localizedMessage}", Toast.LENGTH_LONG)
            .show()


    }

}
