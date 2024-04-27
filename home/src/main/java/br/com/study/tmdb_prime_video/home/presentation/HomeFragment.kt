package br.com.study.tmdb_prime_video.home.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.study.tmdb_prime_video.core.base.BaseAdapter
import br.com.study.tmdb_prime_video.core.mock.parseJsonToModel
import br.com.study.tmdb_prime_video.core.mock.readJsonFromAssets
import br.com.study.tmdb_prime_video.core.R
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.databinding.FragmentHomeBinding
import br.com.study.tmdb_prime_video.home.presentation.adapter.FeaturedMoviesAdapter
import br.com.study.tmdb_prime_video.home.presentation.adapter.MovieCoverAdapter
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

        setupViewPager()
        getFeaturedMovies()
    }

    private fun getFeaturedMovies() {
        homeViewModel.getHomeMovies()
//        lifecycleScope.launchWhenStarted {
//            homeViewModel.popularMoviesUiState.collectLatest { popularMovies ->
//                println(">>>> popularMovies " + popularMovies.data)
//            }
//        }
//        lifecycleScope.launchWhenStarted {
//            homeViewModel.nowPlayingMoviesUiState.collectLatest { nowPlayingMovies ->
//                println(">>>> nowPlayingMovies " + nowPlayingMovies.data)
//            }
//        }
//        lifecycleScope.launchWhenStarted {
//            homeViewModel.upcomingMoviesUiState.collectLatest { upcomingMovies ->
//                println(">>>> upcomingMovies " + upcomingMovies.data)
//            }
//        }
        val popularMovies =
            parseJsonToModel(readJsonFromAssets(requireContext(), "popular_movies_200.json")) as MovieResponse
        createMoviesList("Filmes mais populares", popularMovies)

        val nowPlayingMovies =
            parseJsonToModel(readJsonFromAssets(requireContext(), "now_playing_movies_200.json")) as MovieResponse
        createMoviesList("Filmes em destaque", nowPlayingMovies)

        val upcomingMovies =
            parseJsonToModel(readJsonFromAssets(requireContext(), "upcoming_movies_200.json")) as MovieResponse
        createMoviesList("Prévias", upcomingMovies)
    }

    private fun createMoviesList(title: String, movies: MovieResponse) {
        val listTitle = TextView(requireContext())
        val listTitleParams = LinearLayout.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        listTitle.textSize = 18f
        listTitle.text = title
        listTitle.typeface = Typeface.DEFAULT_BOLD
        listTitle.setTextColor(resources.getColor(android.R.color.white))
        listTitle.layoutParams = listTitleParams
        listTitle.setMargins(32, 0, 32, 0)

        binding.llListsContainer.addView(listTitle)

        val rvMovies = RecyclerView(requireContext())
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        rvMovies.layoutParams = params
        rvMovies.setMargins(0, 8, 0, 8)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL ,false)
        rvMovies.adapter = BaseAdapter {
            MovieCoverAdapter(it)
        }.apply {
            this.items = movies.results?.toMutableList()!!
            this.onClick = {
                Toast.makeText(requireContext(), "lista ta aparecendo", Toast.LENGTH_SHORT).show()
            }
        }
        rvMovies.setLayoutManager(layoutManager)
        rvMovies.visibility = View.VISIBLE

        binding.llListsContainer.addView(rvMovies)
    }

    fun View.setMargins(
        left: Int? = null,
        top: Int? = null,
        right: Int? = null,
        bottom: Int? = null
    ) {
        val lp = layoutParams as? ViewGroup.MarginLayoutParams
            ?: return

        lp.setMargins(
            left ?: lp.leftMargin,
            top ?: lp.topMargin,
            right ?: lp.rightMargin,
            bottom ?: lp.bottomMargin
        )

        layoutParams = lp
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