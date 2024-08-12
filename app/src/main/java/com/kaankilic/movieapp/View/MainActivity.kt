package com.kaankilic.movieapp.View

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       /* val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavMenu,navHostFragment.navController)*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavMenu, navController)

        // BottomNavigationView'in görünürlüğünü kontrol eden listener'ı ekleyin
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isLoginFragment = destination.id == R.id.kullaniciFragment // `kullaniciFragment`'ın ID'sini buraya ekleyin
            val isSplashScreen = destination.id == R.id.splashScreen // `splashScreen`'in ID'sini buraya ekleyin
            binding.bottomNavMenu.visibility = if (isLoginFragment || isSplashScreen) View.GONE else View.VISIBLE
        }
    }






}
