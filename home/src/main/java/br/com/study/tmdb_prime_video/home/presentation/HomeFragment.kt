package br.com.study.tmdb_prime_video.home.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import br.com.study.tmdb_prime_video.core.base.BaseAdapter
import br.com.study.tmdb_prime_video.core.extension.setMargins
import br.com.study.tmdb_prime_video.home.R
import br.com.study.tmdb_prime_video.home.data.model.ImagesMoviesModel
import br.com.study.tmdb_prime_video.home.data.model.ItemModel
import br.com.study.tmdb_prime_video.home.data.model.MovieData
import br.com.study.tmdb_prime_video.home.databinding.FragmentHomeBinding
import br.com.study.tmdb_prime_video.home.presentation.adapter.FeaturedMoviesAdapter
import br.com.study.tmdb_prime_video.home.presentation.adapter.MovieCoverAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val homeItems = mutableListOf<ItemModel>()

    private lateinit var featuredMoviesAdapter: FeaturedMoviesAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var viewPagerHandler: Handler? = null
    private val runnable = Runnable {
        binding.vpFeaturedMovies.currentItem += 1
    }

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

        binding.tlCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tabItem: TabLayout.Tab?) {
                when (tabItem?.position) {
                    0 -> showFilteredItems(homeItems)
                    1 -> showFilteredItems(homeItems.filter {
                        it.type == "Filmes"
                    })

                    2 -> showFilteredItems(homeItems.filter {
                        it.type == "Series"
                    })

                    else -> showFilteredItems(homeItems)
                }
            }

            override fun onTabUnselected(tabItem: TabLayout.Tab?) = Unit

            override fun onTabReselected(tabItem: TabLayout.Tab?) = Unit
        })
    }

    private fun showFilteredItems(filteredItems: List<ItemModel>) {
        showFadeAnimation()
        binding.llListsContainer.removeAllViews()
        createMoviesList(filteredItems)
    }

    private fun showFadeAnimation() {
        val fadeOutAnimation = AnimationUtils.loadAnimation(
            requireContext(),
            br.com.study.tmdb_prime_video.core.R.anim.fade_out
        )
        val fadeInAnimation = AnimationUtils.loadAnimation(
            requireContext(),
            br.com.study.tmdb_prime_video.core.R.anim.fade_in
        )

        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) = Unit

            override fun onAnimationEnd(p0: Animation?) {
                binding.llHomeItemsContainer.startAnimation(fadeInAnimation)
            }

            override fun onAnimationRepeat(p0: Animation?) = Unit
        })

        binding.llHomeItemsContainer.startAnimation(fadeOutAnimation)
    }

    private fun getFeaturedMovies() {
        homeViewModel.getHomeMovies()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.popularMoviesUiState.collectLatest { popularMovies ->
                    addToHomeItems("Filmes", getString(R.string.popular_movies_list_title), popularMovies.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.nowPlayingMoviesUiState.collectLatest { nowPlayingMovies ->
                    addToHomeItems("Filmes", getString(R.string.now_playing_movies_list_title), nowPlayingMovies.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.upcomingMoviesUiState.collectLatest { upcomingMovies ->
                    addToHomeItems("Filmes", getString(R.string.upcoming_movies_list_title), upcomingMovies.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.topRatedMoviesUiState.collectLatest { topRatedMovies ->
                    addToHomeItems("Filmes", getString(R.string.top_rated_movies_list_title), topRatedMovies.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.onTheAirSeriesUiState.collectLatest { onTheAirSeries ->
                    addToHomeItems("Series", getString(R.string.on_the_air_series_list_title), onTheAirSeries.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.popularSeriesUiState.collectLatest { popularSeries ->
                    addToHomeItems("Series", getString(R.string.popular_series_list_title), popularSeries.data?.results)
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.topRatedSeriesUiState.collectLatest { topRatedSeries ->
                    addToHomeItems("Series", getString(R.string.top_rated_series_list_title), topRatedSeries.data?.results)
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.imagesMoviesUiState.collectLatest { images ->
                    setupViewPager(images.data)
                }
            }
        }
    }

    private fun addToHomeItems(type: String, listTitle: String, data: List<MovieData>?) {
        homeItems.add(
            ItemModel(
                data,
                listTitle,
                type
            )
        )

        createMoviesList(homeItems)
    }

    private fun createMoviesList(movies: List<ItemModel>) {
        movies.forEach { item ->
            val listTitle = TextView(requireContext())
            val listTitleParams = LinearLayout.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            listTitle.textSize = 18f
            listTitle.text = item.listTitle
            listTitle.typeface = Typeface.DEFAULT_BOLD
            listTitle.setTextColor(resources.getColor(android.R.color.white))
            listTitle.layoutParams = listTitleParams
            listTitle.setMargins(32, 40, 32, 0)

            binding.llListsContainer.addView(listTitle)

            val rvMovies = RecyclerView(requireContext())
            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rvMovies.layoutParams = params
            rvMovies.setMargins(0, 8, 0, 8)

            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvMovies.adapter = BaseAdapter {
                MovieCoverAdapter(it)
            }.apply {
                item.data?.let { movieData ->
                    this.items = movieData.toMutableList()
                    this.onClick = {

                    }
                }
            }
            rvMovies.setLayoutManager(layoutManager)
            rvMovies.visibility = View.VISIBLE

            binding.llListsContainer.addView(rvMovies)
        }
    }

    private fun setupViewPager(imagesList: ImagesMoviesModel?) = with(binding.vpFeaturedMovies) {
        viewPagerHandler = Looper.myLooper()?.let { Handler(it) }
        featuredMoviesAdapter = FeaturedMoviesAdapter(imagesList?.images?.toMutableList())
        adapter = featuredMoviesAdapter
        offscreenPageLimit = 3
        clipToPadding = false
        clipChildren = false
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        setPageTransformer(transformer)

        val currentPageIndex = 0
        currentItem = currentPageIndex

        registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewPagerHandler?.removeCallbacks(runnable)
                    viewPagerHandler?.postDelayed(runnable, VIEW_PAGER_IMAGE_TIME)

                    updateDotIndicator(position)
                }
            }
        )

        imagesList?.images?.size?.let { createDotIndicators(it) }
    }

    private fun createDotIndicators(count: Int) {

        for (i in 0 until count) {
            val dot = ImageView(requireContext())
            dot.setImageResource(R.drawable.dot_selector)
            val dotParams = LinearLayout.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            dot.layoutParams = dotParams
            dot.setMargins(12, 20, 12, 0)
            binding.iFeaturedMovies.addView(dot)
        }
    }

    private fun updateDotIndicator(position: Int) {

        for (i in 0 until binding.iFeaturedMovies.childCount) {
            val dot = binding.iFeaturedMovies.getChildAt(i) as ImageView
            dot.isSelected = i == position
        }
    }

    override fun onPause() {
        super.onPause()
        viewPagerHandler?.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        viewPagerHandler?.postDelayed(runnable, VIEW_PAGER_IMAGE_TIME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val VIEW_PAGER_IMAGE_TIME = 4000L

        @JvmStatic
        fun newInstance() = HomeFragment().apply {}
    }
}