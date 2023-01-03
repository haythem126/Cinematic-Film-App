package com.saitejajanjirala.imdbclonekotlin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.saitejajanjirala.imdbclonekotlin.R
import com.saitejajanjirala.imdbclonekotlin.ui.bookmark.BookMarkFragment
import com.saitejajanjirala.imdbclonekotlin.ui.detail.DetailFragment
import com.saitejajanjirala.imdbclonekotlin.ui.home.HomeFragment
import com.saitejajanjirala.imdbclonekotlin.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment

        navController = navHostFragment.navController

    }
}