package com.example.movies.presentation.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.Constants
import com.example.movies.core.Constants.Companion.POSTER_BASE_URL
import com.example.movies.core.Resource
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.domain.models.MovieDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(Constants.MOVIE_ID)?.let {
            viewModel.getMovieDetails(it)
        }
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    bindData(it.data)
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.GONE
                }
            }
        }
    }
    private fun bindData(movieDetails: MovieDetails?) {
        if (movieDetails != null) {
            binding.movieDetails = movieDetails
            val moviePosterURL = POSTER_BASE_URL + movieDetails.backdropPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(binding.ivMoviePoster)
        } else {
            Toast.makeText(context, getString(R.string.general_error_msg), Toast.LENGTH_SHORT).show()
        }
    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
}