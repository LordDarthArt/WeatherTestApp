package tk.lorddarthart.weathertest.app.view.fragment.cities_list

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.presenter.fragment.cities_list.CitiesListFragmentPresenter
import tk.lorddarthart.weathertest.app.view.adapter.recycler.WeatherListAdapter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentCitiesListBinding
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import javax.inject.Inject

/** Created by LordDarthArt on 26.10.2019. */
class CitiesListFragment : BaseFragment(), CitiesListFragmentView {
    private lateinit var fragmentBinding: FragmentCitiesListBinding

    @Inject
    @InjectPresenter
    lateinit var citiesListFragmentPresenter: CitiesListFragmentPresenter

    @ProvidePresenter
    fun provideCitiesListPresenter(): CitiesListFragmentPresenter = citiesListFragmentPresenter

    @Deprecated("Should be replaced or removed")
    private lateinit var dialog: ProgressDialog

    private val forecastAdapter: WeatherListAdapter by lazy {
        val itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                citiesListFragmentPresenter.onCardViewTap(view, position)
            }

            override fun onButtonCvMenuClick(view: View, position: Int) {
                // do nothing
            }
        }
        WeatherListAdapter(itemTouchListener)
    }

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var consLayOpen: Animation
    private lateinit var consLayClose: Animation
    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation
    private lateinit var tvOpen: Animation
    private lateinit var tvClose: Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = FragmentCitiesListBinding.inflate(inflater, container, false)

        initialization()

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent()
    }

    override fun initialization() {
        super.initialization()
        setHasOptionsMenu(true)
        initVariables()
        initListeners()
        initAnimations()
    }

    private fun initVariables() {
        dialog = ProgressDialog(App.instance)
        layoutManager = LinearLayoutManager(mainActivity)
    }

    override fun initViews() {
        fragmentBinding.fragmentMainLayoutDarken.isVisible = true
    }

    override fun initListeners() {
        fragmentBinding.fragmentMainFloatingActionButton.setOnClickListener { animateFloatingActionButtonsAction() }
    }

    private fun initAnimations() {
        rotateForward = AnimationUtils.loadAnimation(mainActivity, R.anim.rotate_forward)
        rotateBackward = AnimationUtils.loadAnimation(mainActivity, R.anim.rotate_backward)
        consLayOpen = AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_open)
        consLayClose = AnimationUtils.loadAnimation(mainActivity, R.anim.conslay_close)
        tvOpen = AnimationUtils.loadAnimation(mainActivity, R.anim.tv_open)
        tvClose = AnimationUtils.loadAnimation(mainActivity, R.anim.tv_close)
    }

    override fun setContent() {
        fragmentBinding.apply {
            fragmentMainRecyclerView.layoutManager = layoutManager
            fragmentMainRecyclerView.adapter = forecastAdapter
            fragmentMainFloatingEditText.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

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
            citiesListFragmentPresenter.updateData()
        } else {
            displayData(forecastAdapter.currentList)
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
            R.id.account -> {
                router.navigateTo(Screens.AccountScreen)
            }
            else -> {
                return false
            }
        }
        return true
    }

    override fun showLoadingDialog() {
        dialog = ProgressDialog(mainActivity)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setMessage("Синхронизация…")
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun hideLoadingDialog() {
        dialog.dismiss()
    }

    override fun onPostExecute() {
        citiesListFragmentPresenter.onPostExecute()
        dialog.dismiss()
        swapDarkenAndRecycler(false)
    }

    override fun swapDarkenAndRecycler(darken: Boolean) {
        with(fragmentBinding) {
            fragmentMainRecyclerView.isVisible = !darken
            fragmentMainLayoutDarken.isVisible = darken
        }
    }

    override fun showNetworkError() {
        fragmentBinding.root.longSnackbar("ERROR: NO INTERNET CONNECTION").show()
    }

    override fun displayData(weatherList: List<ForecastEntity>) {
        forecastAdapter.submitList(weatherList.map { it.copy() })
    }
}