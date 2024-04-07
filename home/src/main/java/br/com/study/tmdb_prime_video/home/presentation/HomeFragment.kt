package br.com.study.tmdb_prime_video.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.study.tmdb_prime_video.home.data.model.MovieModelData
import br.com.study.tmdb_prime_video.home.databinding.FragmentHomeBinding
import br.com.study.tmdb_prime_video.home.presentation.adapter.FeaturedMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var featuredMoviesAdapter: FeaturedMoviesAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFeaturedMovies()
    }

    private fun getFeaturedMovies() {
        homeViewModel.getMovies()
        homeViewModel.movies.observe(viewLifecycleOwner) { data ->
            data?.data?.let { movies ->
                featuredMoviesAdapter = FeaturedMoviesAdapter(requireContext(), movies)
                binding.vpFeaturedMovies.adapter = featuredMoviesAdapter
                binding.iFeaturedMovies.setViewPager(binding.vpFeaturedMovies)
            } ?: let {
                data?.error?.let { error ->
                    Toast.makeText(requireContext(), "erro teste", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment().apply {}
    }
}