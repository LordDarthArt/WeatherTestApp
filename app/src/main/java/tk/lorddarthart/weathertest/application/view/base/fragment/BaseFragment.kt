package tk.lorddarthart.weathertest.application.view.base.fragment

import android.content.Context
import android.widget.Toolbar
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.application.App
import tk.lorddarthart.weathertest.application.view.activity.MainActivity
import tk.lorddarthart.weathertest.util.moxy.MvpFragment

abstract class BaseFragment : MvpFragment(), IBaseFragment {
    protected lateinit var mainActivity: MainActivity
    protected lateinit var mainToolbar: Toolbar
    protected lateinit var router: Router

    protected var TAG = this::class.java.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)

        router = App.instance.getRouter()
        mainActivity = context as MainActivity
    }

    override fun initialization() {
        mainActivity.setActionBar(mainToolbar)
    }
}