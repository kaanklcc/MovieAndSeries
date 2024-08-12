package com.kaankilic.movieapp.View

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.databinding.FragmentSeriesDetailBinding
import com.kaankilic.movieapp.databinding.FragmentSplashScreenBinding

class SplashScreen : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
                   val action = SplashScreenDirections.actionSplashScreenToKullaniciFragment()
            Navigation.findNavController(view).navigate(action)

        },2000)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}