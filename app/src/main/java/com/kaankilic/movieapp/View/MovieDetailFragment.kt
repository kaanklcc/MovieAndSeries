package com.kaankilic.movieapp.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.ViewModel.MovieDetailViewModel
import com.kaankilic.movieapp.adapter.CastAdapter
import com.kaankilic.movieapp.databinding.FragmentMovieDetailBinding
import com.kaankilic.movieapp.databinding.FragmentMovieListBinding
import com.kaankilic.movieapp.model.CastMember
import com.kaankilic.movieapp.model.FavouriteMovie
import com.kaankilic.movieapp.model.MovieResult


class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MovieDetailViewModel
    private lateinit var castRecyclerAdapter : CastAdapter
    var movieId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle : MovieDetailFragmentArgs by navArgs()
        val movie = bundle.movies

        binding.titleText.text= movie.originalTitle
        binding.overviewText.text= movie.overview
        binding.releaseChip.text=movie.releaseDate
        binding.rateChip.text=movie.voteAverage.toString()
        binding.peopleChip.text=movie.popularity.toString()

        viewModel= ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.getCastMembers(movie.id)
        viewModel.favMovie(movie)
        viewModel.movieTrailer(movie.id)

        castRecyclerAdapter= CastAdapter(requireContext(), arrayListOf())
        binding.castRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.castRecyclerView.adapter= castRecyclerAdapter

        binding.favView.setOnClickListener { fav(it) }

        binding.backbutton.setOnClickListener {
            val action= MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment()
            Navigation.findNavController(view).navigate(action)
        }


        //trailer ekleme
        binding.trailerView.setOnClickListener {
            viewModel.movieTrailer.observe(viewLifecycleOwner){trailer ->
                trailer?.let {
                    val videoUrl= "https://www.youtube.com/watch?v=${it.key}"
                    val intent= Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                    startActivity(intent)
                }
            }
        }

        val url = "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
        Glide.with(this).load(url).into(binding.posterImageView)

        val urlsecond = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(this).load(urlsecond).override(342,512).into(binding.resimImage)
        observeLiveData()


    }

    fun observeLiveData(){
        viewModel.cast.observe(viewLifecycleOwner){
            castRecyclerAdapter.memberList= it as ArrayList<CastMember>
            castRecyclerAdapter.notifyDataSetChanged()
            binding.castRecyclerView.visibility= View.VISIBLE
        }
    }

    fun fav(view: View) {
        val bundle: MovieDetailFragmentArgs by navArgs()
        val movie = bundle.movies

        val movieResult = MovieResult(
            backdropPath = movie.backdropPath,
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage
        )

        viewModel.favMovie(movieResult)

        val action= MovieDetailFragmentDirections.actionMovieDetailFragmentToFavouriteFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}