package com.kaankilic.movieapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kaankilic.movieapp.ViewModel.FavouriteFragmentViewModel
import com.kaankilic.movieapp.adapter.FavouriteAdapter
import com.kaankilic.movieapp.databinding.FragmentFavouriteBinding
import com.kaankilic.movieapp.model.MovieResult


class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavouriteFragmentViewModel
    var filmId = 0
    private lateinit var favAdapter : FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this)[FavouriteFragmentViewModel::class.java]
        viewModel.roomVerisiniAl(filmId)


        favAdapter= FavouriteAdapter(requireContext(), arrayListOf(),viewModel)
        binding.favRecyclerView.layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.favRecyclerView.adapter= favAdapter

        observeLiveData()

    }


    fun observeLiveData() {
        viewModel.favouriteMovies.observe(viewLifecycleOwner) { movies ->
            favAdapter.favList.clear()
            favAdapter.favList.addAll(movies)
            favAdapter.notifyDataSetChanged()
            binding.favRecyclerView.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}