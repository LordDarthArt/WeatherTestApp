package tk.lorddarthart.weathertest.app.view.fragment.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.presenter.fragment.main.MainFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentMainBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragment : BaseFragment(), MainFragmentView {
    private lateinit var fragmentBinding: FragmentMainBinding

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainFragmentPresenter

    @ProvidePresenter
    fun provideMainPresenter(): MainFragmentPresenter = mainPresenter

    private lateinit var mainFragmentNavigator: SupportAppNavigator

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentMainBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return fragmentBinding.root
    }

    @SuppressLint("CheckResult")
    override fun initialization() {
//        mainToolbar = fragmentBinding.fragmentMainToolbar
        mainFragmentNavigator = SupportAppNavigator(activity, R.id.main_fragment_fm_container)
        val newRouter = Router()
        setHasOptionsMenu(true)
        super.initialization()

        Observable.just(requireActivity().supportFragmentManager)
            .subscribeOn(Schedulers.io())
            .delay(100, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                App.instance.getRouter().navigateTo(Screens.MainScreen.CitiesListScreen)
            }, {
                Snackbar.make(fragmentBinding.root, "Error occured", Snackbar.LENGTH_SHORT)
            })

        initVariables()
        initListeners()
        initAnimations()
    }

    private fun initVariables() {
        // do something
    }

    override fun initViews() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    private fun initAnimations() {
        // do something
    }

    override fun setContent() {
        // do something
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_setcity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.account -> { App.instance.getNavigatorHolder().setNavigator(mainActivity.navigator); router.navigateTo(Screens.AccountScreen); true }
            else -> { false }
        }
    }

    fun setCurrentNavigator() {
        App.instance.getNavigatorHolder().setNavigator(mainFragmentNavigator)
    }

    fun removeCurrentNavigator() {
        App.instance.getNavigatorHolder().removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        setCurrentNavigator()
    }

    override fun onPause() {
        super.onPause()
        removeCurrentNavigator()
    }
}
