package com.kaankilic.movieapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.ViewModel.SeriesListViewModel
import com.kaankilic.movieapp.adapter.OnTheAirAdapter
import com.kaankilic.movieapp.adapter.PopularSeriesAdapter
import com.kaankilic.movieapp.adapter.TopRatedSeriesAdapter
import com.kaankilic.movieapp.databinding.FragmentMovieListBinding
import com.kaankilic.movieapp.databinding.FragmentSeriesListBinding
import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.model.Result


class SeriesListFragment : Fragment() {
    private var _binding: FragmentSeriesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SeriesListViewModel
    private lateinit var popularSeriesAdapter : PopularSeriesAdapter
    private lateinit var topRatedSeriesAdapter : TopRatedSeriesAdapter
    private lateinit var onTheAirSeriesAdapter : OnTheAirAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SeriesListViewModel::class.java]
        viewModel.getPopularSeries()
        viewModel.getTopRatedSeries()
        viewModel.getOnTheAirSeries()
        adapter()
        observeLiveData()
        setupSearchView()

    }

    private fun observeLiveData(){
        viewModel.popularSeries.observe(viewLifecycleOwner){
            popularSeriesAdapter.seriesList = it as ArrayList<Result>
            popularSeriesAdapter.notifyDataSetChanged()
            binding.popularSeriesRecyclerView.visibility=View.VISIBLE
        }
        viewModel.topRatedSeries.observe(viewLifecycleOwner){
            topRatedSeriesAdapter.seriesList= it as ArrayList<Result>
            topRatedSeriesAdapter.notifyDataSetChanged()
            binding.topRatedSeriesRecyclerView.visibility= View.VISIBLE

        }
        viewModel.onTheAirSeries.observe(viewLifecycleOwner){
            onTheAirSeriesAdapter.seriesList= it as ArrayList<Result>
            onTheAirSeriesAdapter.notifyDataSetChanged()
            binding.onTheAirSeriesRecyclerView.visibility= View.VISIBLE
        }
    }
    private fun adapter(){
        popularSeriesAdapter= PopularSeriesAdapter(requireContext(), arrayListOf())
        binding.popularSeriesRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.popularSeriesRecyclerView.adapter= popularSeriesAdapter

        topRatedSeriesAdapter= TopRatedSeriesAdapter(requireContext(), arrayListOf())
        binding.topRatedSeriesRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.topRatedSeriesRecyclerView.adapter= topRatedSeriesAdapter

        onTheAirSeriesAdapter= OnTheAirAdapter(requireContext(), arrayListOf())
        binding.onTheAirSeriesRecyclerView.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.onTheAirSeriesRecyclerView.adapter=onTheAirSeriesAdapter

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
        val popularFilteredList = viewModel.popularSeries.value?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        val topRatedFilteredList = viewModel.topRatedSeries.value?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        val upcomingFilteredList = viewModel.onTheAirSeries.value?.filter {
            it.name.contains(query, ignoreCase = true)
        } ?: arrayListOf()

        popularSeriesAdapter.seriesList = popularFilteredList as ArrayList<Result>
        topRatedSeriesAdapter.seriesList = topRatedFilteredList as ArrayList<Result>
        onTheAirSeriesAdapter.seriesList = upcomingFilteredList as ArrayList<Result>

        popularSeriesAdapter.notifyDataSetChanged()
        topRatedSeriesAdapter.notifyDataSetChanged()
        onTheAirSeriesAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}