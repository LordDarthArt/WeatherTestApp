package tk.lorddarthart.weathertest.app.view.fragment.main.cities_list

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.presenter.fragment.main.cities_list.CitiesListFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentCitiesListBinding
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.helper.SharedPreferencesHelper
import tk.lorddarthart.weathertest.util.helper.logDebug

/**
 * Created by LordDarthArt on 26.10.2019.
 */
class CitiesListFragment : BaseFragment(), CitiesListFragmentView {
    override var citiesListFragmentBinding: FragmentCitiesListBinding? = null

    @InjectPresenter
    lateinit var citiesListFragmentPresenter: CitiesListFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.citiesListFragmentBinding = FragmentCitiesListBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return citiesListFragmentBinding!!.root
    }

    @Deprecated("Should be replaced or removed")
    private lateinit var dialog: ProgressDialog

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var consLayOpen: Animation
    private lateinit var consLayClose: Animation
    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation
    private lateinit var tvOpen: Animation
    private lateinit var tvClose: Animation

    override fun initialization() {
        setHasOptionsMenu(true)
        super.initialization()
        initVariables()
        initListeners()
        initAnimations()
    }

    private fun initVariables() {
        dialog = ProgressDialog(App.instance)
        layoutManager = LinearLayoutManager(mainActivity)
    }

    override fun initViews() {
        this.citiesListFragmentBinding?.fragmentMainLayoutDarken?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun initListeners() {
        this.citiesListFragmentBinding?.fragmentMainFloatingActionButton
                ?.setOnClickListener {
                    animateFloatingActionButtonsAction()
                }
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
        SharedPreferencesHelper.checkOnStart()
        this.citiesListFragmentBinding?.fragmentMainRecyclerView?.layoutManager = layoutManager
        this.citiesListFragmentBinding?.fragmentMainFloatingEditText?.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        logDebug(this@CitiesListFragment, "afterTextChanged called")
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        logDebug(this@CitiesListFragment, "beforeTextChanged called")
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        logDebug(this@CitiesListFragment, "onTextChanged called")
                        with(this@CitiesListFragment.citiesListFragmentBinding!!) {
                            fragmentMainFloatingEditText?.let { editText ->
                                fragmentMainFloatingActionButton?.let { floatingActionButton ->
                                    if (editText.text.isNotEmpty() && floatingActionButton.rotation != -45f) {
                                        with(floatingActionButton) {
                                            setImageResource(android.R.drawable.ic_menu_send)
                                            rotation = -45f
                                            setOnClickListener {
                                                rotation = 0f
                                                setImageResource(R.drawable.ic_baseline_plus_24px)
                                                animateFloatingActionButtonsAction()
                                                setOnClickListener { animateFloatingActionButtonsAction() }
                                                fragmentMainRecyclerView?.visibility = View.INVISIBLE
                                            }
                                        }
                                    } else if (editText.text.isEmpty()) {
                                        with(floatingActionButton) {
                                            setImageResource(R.drawable.ic_baseline_plus_24px)
                                            rotation = 0f
                                            setOnClickListener {
                                                animateFloatingActionButtonsAction()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
        try {
            citiesListFragmentPresenter.updateData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun animateFloatingActionButtonsAction() {
        with(this.citiesListFragmentBinding!!) {
            if (fragmentMainLayoutDarken.visibility == View.VISIBLE) {
                fragmentMainFloatingActionButton?.startAnimation(rotateForward)
                with(fragmentMainFloatingTextBox) {
                    startAnimation(tvClose)
                    fragmentMainFloatingTextBox?.isClickable = false
                    fragmentMainFloatingTextBox?.visibility = View.GONE
                }
                with(fragmentMainLayoutDarken) {
                    startAnimation(consLayClose)
                    isClickable = false
                    visibility = View.GONE
                }
            } else {
                fragmentMainFloatingActionButton?.startAnimation(rotateBackward)
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

    private fun initializeAdapter() {
        val itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                citiesListFragmentPresenter.onCardViewTap(view, position)
            }

            override fun onButtonCvMenuClick(view: View, position: Int) {
                // do nothing
            }
        }
//        val recyclerViewAdapter = RecyclerViewAdapter(mainActivity, weather, itemTouchListener)
//        citiesListFragmentBinding.fragmentMainRecyclerView?.adapter = recyclerViewAdapter
    }

    override fun showSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(this.citiesListFragmentBinding?.fragmentMainRootContainer?.windowToken, InputMethodManager.SHOW_FORCED, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun hideSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.citiesListFragmentBinding?.fragmentMainRootContainer?.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

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
        with(this.citiesListFragmentBinding!!) {
            if (darken) {
                fragmentMainRecyclerView?.visibility = View.GONE
                fragmentMainLayoutDarken?.visibility = View.VISIBLE
            } else {
                fragmentMainRecyclerView?.visibility = View.VISIBLE
                fragmentMainLayoutDarken?.visibility = View.GONE
            }
        }
    }

    override fun showNetworkError() {
        citiesListFragmentBinding?.root?.longSnackbar("ERROR: NO INTERNET CONNECTION")?.show()
    }
}