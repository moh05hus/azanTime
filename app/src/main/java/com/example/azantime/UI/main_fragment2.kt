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
import com.example.azantime.model.solat
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

        // get cite from search fragment and but city in the value
        val city = arguments?.getString("CITY").toString()
        getSalat(city)

        // on back btn clicked wll be go to search fragment
        binding.backBtn.setOnClickListener {
            replaceFragment(search_fragment1())
        }


        return binding.root
    }
    // to replace any fragment do you want latter
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.commit()
    }

    private fun getSalat(city: String) {
            lifecycleScope.launch(Dispatchers.Main) {
                salatRepo().getSalat(city).collect {
                    when (it) {
                        is State.Fail -> onFail()
                        State.Loading -> onLoading()
                        is State.Success -> { viewOnUI(it.data.items.first(), city)
                            onSuccess()}
                    }
                }
            }

    }
    private fun onFail (){
        showErrorAndHideData()
    }
    private fun onLoading(){
        showProgressBarAndHideData()
    }
    private fun onSuccess(){
        hideProgressBarAndShowData()

    }
    private fun showErrorAndHideData() {
        binding.apply {
          errorAnimation.visibility = View.VISIBLE
            citeNotFoundText.visibility =View.VISIBLE
            constraintLayout.visibility = View.INVISIBLE
            cityTv.visibility = View.INVISIBLE
            dateTv.visibility = View.INVISIBLE
        }

    }

    private fun showProgressBarAndHideData() {
        binding.apply {
            progressBarMainFragment.visibility = View.VISIBLE
            constraintLayout.visibility = View.INVISIBLE
            cityTv.visibility = View.INVISIBLE
            dateTv.visibility = View.INVISIBLE
        }
    }

    private fun hideProgressBarAndShowData() {
        binding.apply {
            progressBarMainFragment.visibility = View.INVISIBLE
            constraintLayout.visibility = View.VISIBLE
            cityTv.visibility = View.VISIBLE
            dateTv.visibility = View.VISIBLE
        }
    }

    private fun viewOnUI(salat: Item,city: String) {
        //Edit text in ui from API
        binding.apply {
            alfajrTimeTv.text = salat.fajr
            alzuhrTimeTv.text = salat.dhuhr
            aleasrTimeTv.text = salat.asr
            almaghribTimeTv.text = salat.maghrib
            aleishaTimeTv.text = salat.isha
            dateTv.text = "Today date is: ${salat.dateFor}"
            cityTv.text = city

        }
    }


}
