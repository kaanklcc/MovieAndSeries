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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.R
import com.kaankilic.movieapp.ViewModel.SeriesDetailViewModel
import com.kaankilic.movieapp.adapter.SeriesCastAdapter
import com.kaankilic.movieapp.databinding.FragmentMovieDetailBinding
import com.kaankilic.movieapp.databinding.FragmentSeriesDetailBinding
import com.kaankilic.movieapp.model.CastMember

class SeriesDetailFragment : Fragment() {
    private var _binding: FragmentSeriesDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SeriesDetailViewModel
    private lateinit var seriesCastAdapter: SeriesCastAdapter

    var movieID= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: SeriesDetailFragmentArgs by navArgs()
        val series = bundle.series

        binding.titleText.text= series.name
        binding.releaseChip.text=series.firstAirDate
        binding.peopleChip.text=series.originalLanguage
        binding.rateChip.text=series.voteAverage.toString()
       // binding.peopleChip.text=series.voteCount.toString()
        binding.overviewText.text=series.overview

        viewModel= ViewModelProvider(this)[SeriesDetailViewModel::class.java]
        viewModel.getSeriesCast(series.id)
        viewModel.seriesTrailer(series.id)

        seriesCastAdapter= SeriesCastAdapter(requireContext(), arrayListOf())
        binding.castRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.castRecyclerView.adapter= seriesCastAdapter



        binding.backbutton.setOnClickListener {
            val action= SeriesDetailFragmentDirections.actionSeriesDetailFragmentToSeriesListFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.seriesTrailerView.setOnClickListener {
            viewModel.seriesTrailer.observe(viewLifecycleOwner){trailer ->
                trailer?.let {
                    val videoUrl= "https://www.youtube.com/watch?v=${it.key}"
                    val intent= Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                    startActivity(intent)
                }
            }
        }



        val url = "https://image.tmdb.org/t/p/w500${series.backdropPath}"
        Glide.with(this).load(url).override(342,512).into(binding.posterImageView)
        val urlsecond = "https://image.tmdb.org/t/p/w500${series.posterPath}"
        Glide.with(this).load(urlsecond).override(342,512).into(binding.resimImage)
        observeLiveData()


    }

    fun observeLiveData(){
        viewModel.seriesCast.observe(viewLifecycleOwner){
            seriesCastAdapter.castList= it as ArrayList<CastMember>
            seriesCastAdapter.notifyDataSetChanged()
            binding.castRecyclerView.visibility= View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}