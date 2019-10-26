package tk.lorddarthart.weathertest.app.view.fragment.main

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.presenter.fragment.main.MainFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.cities_list.CitiesListFragment
import tk.lorddarthart.weathertest.databinding.FragmentMainBinding

class MainFragment : BaseFragment(), MainFragmentView {
    override var fragmentMainBinding: FragmentMainBinding? = null

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter

    private var mainFragmentNavigator = SupportAppNavigator(mainActivity, R.id.main_fragment_fm_container)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return fragmentMainBinding!!.root
    }

    override fun initialization() {
        mainToolbar = fragmentMainBinding!!.fragmentMainToolbar
        setHasOptionsMenu(true)
        super.initialization()
        router.replaceScreen(Screens.MainScreen.CitiesListScreen)
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
        when (item.itemId) {
            R.id.account -> {
                App.instance.getNavigatorHolder().setNavigator(mainActivity.navigator)
                router.navigateTo(Screens.AccountScreen)
            }
            else -> {
                return false
            }
        }
        return true
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
