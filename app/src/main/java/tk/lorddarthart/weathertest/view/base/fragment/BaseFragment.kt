package tk.lorddarthart.weathertest.view.base.fragment

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.view.activity.MainActivity
import javax.inject.Inject

abstract class BaseFragment : MvpAppCompatFragment(), HasAndroidInjector, IBaseFragment {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var router: Router

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun initialization() {
        // do something
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}