package com.example.movies.presentation.popular_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.core.Resource
import com.example.movies.databinding.FragmentPopularBinding
import com.example.movies.domain.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import android.content.res.Configuration
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.core.Constants

@AndroidEntryPoint
class PopularFragment : Fragment(), PopularAdapter.MovieItemListener {

    private lateinit var binding: FragmentPopularBinding
    private val viewModel: PopularViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onClickedItem(movieId: Int) {
        findNavController().navigate(
            R.id.action_popularFragment2_to_movieDetailsFragment,
            bundleOf(Constants.MOVIE_ID to movieId)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { moviesList -> renderList(moviesList) }
                    binding.moviesRv.visibility = View.VISIBLE
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    binding.errorTxt.visibility = View.VISIBLE
                    binding.errorTxt.text = it.message
                }
            }
        }
    }
    private fun hideProgressBar() {
       binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun renderList(movies: List<Movie>) {
        popularAdapter.addData(movies)
    }

    private fun setupUI() {
        binding.moviesRv.apply {
            layoutManager = GridLayoutManager(
                context,
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 4 else 6
            )
            setHasFixedSize(true)
        }
        popularAdapter = PopularAdapter(this, arrayListOf())
        binding.moviesRv.adapter = popularAdapter
    }
}