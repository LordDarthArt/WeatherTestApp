package tk.lorddarthart.weathertest.app.view.fragment.citieslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.data.entities.ForecastEntity
import tk.lorddarthart.presenter.fragment.cities_list.CitiesListFragmentPresenter
import tk.lorddarthart.presenter.fragment.cities_list.CitiesListFragmentView
import tk.lorddarthart.weathertest.app.view.adapter.recycler.ForecastsAdapter
import tk.lorddarthart.weathertest.app.view.adapter.recycler.ForecastsSkeletonAdapter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.app.view.fragment.pages.general.ExtendedFragmentGeneral
import tk.lorddarthart.weathertest.databinding.FragmentCitiesListBinding
import tk.lorddarthart.utils.helper.logDebug
import javax.inject.Inject

/** Created by LordDarthArt on 26.10.2019. */
class CitiesListFragment : BaseFragment(), CitiesListFragmentView {
    private lateinit var fragmentBinding: FragmentCitiesListBinding

    @Inject
    @InjectPresenter
    lateinit var citiesListFragmentPresenter: CitiesListFragmentPresenter

    @ProvidePresenter
    fun provideCitiesListPresenter(): CitiesListFragmentPresenter = citiesListFragmentPresenter

    private val layoutManager: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    private val forecastsAdapter: ForecastsAdapter by lazy {
        val itemTouchListener = object : tk.lorddarthart.utils.OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                citiesListFragmentPresenter.onCardViewTap(view, position)
            }

            override fun onButtonCvMenuClick(view: View, position: Int) {
                // do nothing
            }
        }
        ForecastsAdapter(itemTouchListener)
    }

    private val forecastsSkeletonAdapter: ForecastsSkeletonAdapter by lazy {
        ForecastsSkeletonAdapter(20)
    }

    private val consLayOpen: Animation by lazy { AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_open) }
    private val consLayClose: Animation by lazy { AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_close) }
    private val rotateForward: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_forward) }
    private val rotateBackward: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_backward) }
    private val tvOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.tv_open) }
    private val tvClose: Animation by lazy { AnimationUtils.loadAnimation(mainActivity, R.anim.tv_close) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentBinding = FragmentCitiesListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        setContent()
    }

    override fun initialization() {
        super.initialization()
        initListeners()
    }

    override fun initViews() {
        fragmentBinding.fragmentMainLayoutDarken.isVisible = true
    }

    override fun initListeners() {
        fragmentBinding.fragmentMainFloatingActionButton.setOnClickListener { animateFloatingActionButtonsAction() }
        fragmentBinding.fragmentMainPullToRefresh.setOnRefreshListener {
            fragmentBinding.fragmentMainPullToRefresh.setRefreshing(false)
            citiesListFragmentPresenter.updateData()
        }
    }

    override fun setContent() {
        fragmentBinding.apply {
            try {
                fragmentMainRecyclerView.layoutManager = layoutManager
            } catch (e: Exception) {
                logDebug("Seems like layoutManager has been already attached")
            }
            fragmentMainRecyclerView.adapter = forecastsAdapter
            fragmentMainFloatingEditText.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {}

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        with(this@CitiesListFragment.fragmentBinding) {
                            if (fragmentMainFloatingEditText.text.isNotEmpty() && fragmentMainFloatingActionButton.rotation != -45f) {
                                with(fragmentMainFloatingActionButton) {
                                    setImageResource(android.R.drawable.ic_menu_send)
                                    rotation = -45f
                                    setOnClickListener {
                                        rotation = 0f
                                        setImageResource(R.drawable.ic_baseline_plus_24px)
                                        animateFloatingActionButtonsAction()
                                        setOnClickListener { animateFloatingActionButtonsAction() }
                                        fragmentMainRecyclerView.visibility = View.INVISIBLE
                                    }
                                }
                            } else if (fragmentMainFloatingEditText.text.isEmpty()) {
                                with(fragmentMainFloatingActionButton) {
                                    setImageResource(R.drawable.ic_baseline_plus_24px)
                                    rotation = 0f
                                    setOnClickListener {
                                        animateFloatingActionButtonsAction()
                                    }
                                }
                            }
                        }
                    }
                })
        }
        if (citiesListFragmentPresenter.cities == null) {
            showLoading()
            citiesListFragmentPresenter.updateData()
        } else {
            displayData(forecastsAdapter.currentList)
        }
    }

    override fun animateFloatingActionButtonsAction() {
        with(this.fragmentBinding) {
            if (fragmentMainLayoutDarken.visibility == View.VISIBLE) {
                fragmentMainFloatingActionButton.startAnimation(rotateForward)
                with(fragmentMainFloatingTextBox) {
                    startAnimation(tvClose)
                    fragmentMainFloatingTextBox.isClickable = false
                    fragmentMainFloatingTextBox.visibility = View.GONE
                }
                with(fragmentMainLayoutDarken) {
                    startAnimation(consLayClose)
                    isClickable = false
                    visibility = View.GONE
                }
            } else {
                fragmentMainFloatingActionButton.startAnimation(rotateBackward)
                with(fragmentMainFloatingTextBox) {
                    startAnimation(tvOpen)
                    isClickable = true
                    visibility = View.VISIBLE
                }
                with(fragmentMainLayoutDarken) {
                    isClickable = true
                    startAnimation(consLayOpen)
                    visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_setcity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.account -> { router.navigateTo(Screens.AccountScreen) }
            else -> { return false }
        }
        return true
    }

    override fun showLoading() {
        fragmentBinding.fragmentMainRecyclerView.adapter = forecastsSkeletonAdapter
        fragmentBinding.shimmer.showShimmer(true)
        fragmentBinding.shimmer.startShimmer()
    }

    override fun hideLoading() {
        fragmentBinding.shimmer.apply {
            stopShimmer()
            hideShimmer()
        }
    }

    override fun onPostExecute() {
        citiesListFragmentPresenter.onPostExecute()
        swapDarkenAndRecycler(false)
    }

    override fun swapDarkenAndRecycler(darken: Boolean) {
        fragmentBinding.apply {
            fragmentMainRecyclerView.isVisible = !darken
            fragmentMainLayoutDarken.isVisible = darken
        }
    }

    override fun showNetworkError() {
        fragmentBinding.root.longSnackbar(getString(R.string.network_error)).show()
    }

    override fun displayData(forecasts: List<ForecastEntity>) {
        fragmentBinding.fragmentMainRecyclerView.adapter = forecastsAdapter
        forecastsAdapter.submitList(forecasts.map { it.copy() })
    }

    override fun openExtendedInfo() {
        ExtendedFragmentGeneral().show((activity?.supportFragmentManager as FragmentManager), ExtendedFragmentGeneral::class.java.simpleName)
    }
}