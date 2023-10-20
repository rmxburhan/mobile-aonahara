package com.example.studentclass.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.studentclass.R
import com.example.studentclass.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            ?.findNavController()
        val bottomNavHostFragment : BottomNavigationView = findViewById(R.id.bottomNav)
        navController?.let { bottomNavHostFragment.setupWithNavController(it) }

//        bottomNavHostFragment.setOnItemSelectedListener(object : OnNavigationItemSelectedListener,
//            NavigationBarView.OnItemSelectedListener {
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                if (item.itemId == R.id.homeFragment) {
//                    navController?.navigate(R.id.homeFragment)
//                } else if (item.itemId == R.id.mediaFragment) {
//                    navController?.navigate(R.id.mediaFragment)
//                } else if (item.itemId == R.id.userFragment) {
//                    navController?.navigate(R.id.userFragment)
//                }
//            }
//        })
    }
}