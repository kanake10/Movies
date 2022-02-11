package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.databinding.ActivityMoviesBinding
import com.example.movies.db.MovieDatabase
import com.example.movies.repository.MoviesRepository
import com.example.movies.ui.viewmodel.MoviesViewModel
import com.example.movies.ui.viewmodel.MoviesViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    lateinit var viewModel : MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val moviesRepository = MoviesRepository(MovieDatabase(this))
        val viewModelProviderFactory = MoviesViewModelProviderFactory(moviesRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MoviesViewModel::class.java)

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.moviesNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.popularFragment, R.id.searchFragment, R.id.likedFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}