package br.com.study.tmdb_prime_video.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
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
        homeViewModel.getPopularMovies()
        homeViewModel.movies.observe(viewLifecycleOwner) { data ->
            data?.data?.let { movies ->
                setupViewPager()
            } ?: let {
                data?.error?.let { error ->
                    Toast.makeText(requireContext(), "erro teste", Toast.LENGTH_LONG).show()
                }
            }
        }
        setupViewPager()
    }

    private fun setupViewPager() {
        featuredMoviesAdapter = FeaturedMoviesAdapter(imagesTest())
        binding.vpFeaturedMovies.adapter = featuredMoviesAdapter

        binding.vpFeaturedMovies.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val currentPageIndex = 0
        binding.vpFeaturedMovies.currentItem = currentPageIndex

        binding.vpFeaturedMovies.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)


                }
            }
        )
    }

    /*
        Listas a serem criadas
            Filmes
                Now playing como Filmes em destaque
                Popular como Filmes mais populares
                Upcoming como Prévias
                Top Rated como Filmes com as melhores notas
            Séries
                On the Air como Séries em andamento
                Popular como Séries mais populares
                Top Rated como Séries com as melhores notas
     */

    private fun imagesTest(): ArrayList<String> {
        return arrayListOf(
            "https://i.insider.com/660f07f116bde8d4ead5de2b?width=1200&format=jpeg",
            "https://c.biztoc.com/p/13336510d1ab9c05/og.webp",
            "https://c.biztoc.com/p/374549b887d8ffce/s.webp",
            "https://c.biztoc.com/p/bb926a9742d53fbf/s.webp",
            "https://c.biztoc.com/274/og.png"
        )
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