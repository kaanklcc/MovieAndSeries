package com.kaankilic.movieapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.databinding.FragmentKullaniciBinding



class KullaniciFragment : Fragment() {
    private var _binding: FragmentKullaniciBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKullaniciBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signButton.setOnClickListener { sign(it) }
        binding.loginButton.setOnClickListener { login(it) }

        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null){
            val action= KullaniciFragmentDirections.actionKullaniciFragmentToMovieListFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun sign(view: View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    val action= KullaniciFragmentDirections.actionKullaniciFragmentToMovieListFragment()
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.kullaniciFragment, true) // KullaniciFragment'ı geri yığınından temizler
                        .build()

                    Navigation.findNavController(view).navigate(action,navOptions)

                }
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }
    fun login(view: View){
        val email= binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isNotEmpty()&& password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val action = KullaniciFragmentDirections.actionKullaniciFragmentToMovieListFragment()
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.kullaniciFragment, true) // KullaniciFragment'ı geri yığınından temizler
                    .build()
                Navigation.findNavController(view).navigate(action,navOptions)

            }.addOnFailureListener {exception ->

                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}