package com.kaankilic.movieapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kaankilic.movieapp.ViewModel.MovieListViewModel
import com.kaankilic.movieapp.adapter.MovieAdapter
import com.kaankilic.movieapp.adapter.TopRatedAdapter
import com.kaankilic.movieapp.adapter.TopRatedSeriesAdapter
import com.kaankilic.movieapp.adapter.UpcomingAdapter
import com.kaankilic.movieapp.databinding.FragmentMovieListBinding
import com.kaankilic.movieapp.model.MovieResult


class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieRecyclerAdapter : MovieAdapter
    private lateinit var  topRatedAdapter : TopRatedAdapter
    private lateinit var upcomingAdapter : UpcomingAdapter
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[MovieListViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
        binding.closeView.setOnClickListener {
            Snackbar.make(view,"Are you sure you want to log out?",Snackbar.LENGTH_INDEFINITE).setAction("Yes",View.OnClickListener {
                auth.signOut()
                val action= MovieListFragmentDirections.actionMovieListFragmentToKullaniciFragment()
                Navigation.findNavController(view).navigate(action)
            }).show()

        }
        adapter()
        observeLiveData()
        setupSearchView()

    }

    private fun observeLiveData(){
        viewModel.popularMovies.observe(viewLifecycleOwner){
            movieRecyclerAdapter.movieListesi= it as ArrayList<MovieResult>
            movieRecyclerAdapter.notifyDataSetChanged()
            binding.popularMoviesRecyclerView.visibility=View.VISIBLE

        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner){
            topRatedAdapter.movieList = it as ArrayList<MovieResult>
            topRatedAdapter.notifyDataSetChanged()
            binding.topRatedRecyclerView.visibility= View.VISIBLE
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner){
            upcomingAdapter.movieList= it as ArrayList<MovieResult>
            upcomingAdapter.notifyDataSetChanged()
            binding.upcomingRecyclerView.visibility= View.VISIBLE

        }

    }
    private fun adapter(){
        upcomingAdapter= UpcomingAdapter(requireContext(), arrayListOf())
        binding.upcomingRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.upcomingRecyclerView.adapter= upcomingAdapter

        topRatedAdapter= TopRatedAdapter(requireContext(), arrayListOf())
        binding.topRatedRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.topRatedRecyclerView.adapter= topRatedAdapter

        movieRecyclerAdapter = MovieAdapter(requireContext(), arrayListOf())
        binding.popularMoviesRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.popularMoviesRecyclerView.adapter= movieRecyclerAdapter

    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterMovies(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterMovies(it) }
                return true
            }
        })
    }
    private fun filterMovies(query: String) {
        val popularFilteredList = viewModel.popularMovies.value?.filter {
            it.originalTitle.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        val topRatedFilteredList = viewModel.topRatedMovies.value?.filter {
            it.originalTitle.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        val upcomingFilteredList = viewModel.upcomingMovies.value?.filter {
            it.originalTitle.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        movieRecyclerAdapter.movieListesi = popularFilteredList as ArrayList<MovieResult>
        topRatedAdapter.movieList = topRatedFilteredList as ArrayList<MovieResult>
        upcomingAdapter.movieList = upcomingFilteredList as ArrayList<MovieResult>

        movieRecyclerAdapter.notifyDataSetChanged()
        topRatedAdapter.notifyDataSetChanged()
        upcomingAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}