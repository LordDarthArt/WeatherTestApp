package tk.lorddarthart.weathertest.app.view.base.fragment

import android.content.Context
import androidx.appcompat.widget.Toolbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.view.activity.MainActivity
import tk.lorddarthart.weathertest.util.moxy.MvpFragment
import javax.inject.Inject

abstract class BaseFragment : MvpAppCompatFragment(), HasAndroidInjector, IBaseFragment {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var mainActivity: MainActivity
    protected lateinit var mainToolbar: Toolbar
    protected lateinit var router: Router

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        router = App.instance.getRouter()
        mainActivity = context as MainActivity
    }

    override fun initialization() {
        if (::mainToolbar.isInitialized) {
            mainActivity.setSupportActionBar(mainToolbar)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}