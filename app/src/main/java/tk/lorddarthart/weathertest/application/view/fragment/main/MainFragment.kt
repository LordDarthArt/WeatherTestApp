package tk.lorddarthart.weathertest.application.view.fragment.main

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.App
import tk.lorddarthart.weathertest.application.model.forecast.current.CurrentForecast
import tk.lorddarthart.weathertest.application.presenter.main.MainFragmentPresenter
import tk.lorddarthart.weathertest.application.view.adapter.recycler.RecyclerViewAdapter
import tk.lorddarthart.weathertest.application.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentMainBinding
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.helper.SharedPreferencesHelper.checkOnStart

class MainFragment : BaseFragment(), MainFragmentView {
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var dialog: ProgressDialog
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var consLayOpen: Animation
    private lateinit var consLayClose: Animation
    private lateinit var rotateForward: Animation
    private lateinit var rotateBackward: Animation
    private lateinit var tvOpen: Animation
    private lateinit var tvClose: Animation

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: MainFragmentPresenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
        )

        initialization()

        return fragmentMainBinding.fragmentMainRootContainer
    }

    override fun initialization() {
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
        fragmentMainBinding.fragmentMainLayoutDarken?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun initListeners() {
        fragmentMainBinding.fragmentMainFloatingActionButton
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
        checkOnStart()
        fragmentMainBinding.fragmentMainRecyclerView?.layoutManager = layoutManager
        fragmentMainBinding.fragmentMainFloatingEditText?.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        // do nothing
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        // do nothing
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        with(fragmentMainBinding) {
                            fragmentMainFloatingEditText?.let { editText ->
                                fragmentMainFloatingActionButton?.let { floatingActionButton ->
                                    if (editText.text.isNotEmpty() && floatingActionButton.rotation != -45f) {
                                        floatingActionButton.setImageResource(android.R.drawable.ic_menu_send)
                                        floatingActionButton.rotation = -45f
                                        floatingActionButton.setOnClickListener {
                                            floatingActionButton.rotation = 0f
                                            floatingActionButton.setImageResource(R.drawable.ic_baseline_plus_24px)
                                            animateFloatingActionButtonsAction()
                                            floatingActionButton.setOnClickListener { animateFloatingActionButtonsAction() }
                                            fragmentMainRecyclerView?.visibility = View.INVISIBLE
                                            try {
                                                // do something
                                            } catch (e: Exception) {
                                                e.printStackTrace()
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
            presenter.updateData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun animateFloatingActionButtonsAction() {
        with(fragmentMainBinding) {
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
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun initializeAdapter() {
        val itemTouchListener = object : OnItemTouchListener {
            override fun onCardViewTap(view: View, position: Int) {
                presenter.onCardViewTap(view, position)
            }

            override fun onButtonCvMenuClick(view: View, position: Int) {
                // do nothing
            }
        }
//        val recyclerViewAdapter = RecyclerViewAdapter(mainActivity, weather, itemTouchListener)
//        fragmentMainBinding.fragmentMainRecyclerView?.adapter = recyclerViewAdapter
    }

    override fun showSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(fragmentMainBinding.fragmentMainRootContainer.windowToken, InputMethodManager.SHOW_FORCED, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun hideSoftKeyboard() {
        try {
            val inputMethodManager = mainActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(fragmentMainBinding.fragmentMainRootContainer.windowToken, 0)
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
        presenter.onPostExecute()
        dialog.dismiss()
        swapDarkenAndRecycler(false)
    }

    override fun swapDarkenAndRecycler(darken: Boolean) {
        with(fragmentMainBinding) {
            if (darken) {
                fragmentMainRecyclerView?.visibility = View.GONE
                fragmentMainLayoutDarken?.visibility = View.VISIBLE
            } else {
                fragmentMainRecyclerView?.visibility = View.VISIBLE
                fragmentMainLayoutDarken?.visibility = View.GONE
            }
        }
    }
}
