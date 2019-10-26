package tk.lorddarthart.weathertest.app.view.base.fragment

import android.content.Context
import androidx.appcompat.widget.Toolbar
import ru.terrakok.cicerone.Router
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.view.activity.MainActivity
import tk.lorddarthart.weathertest.util.moxy.MvpFragment

abstract class BaseFragment : MvpFragment(), IBaseFragment {
    protected lateinit var mainActivity: MainActivity
    protected lateinit var mainToolbar: Toolbar
    protected lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)

        router = App.instance.getRouter()
        mainActivity = context as MainActivity
    }

    override fun initialization() {
        mainActivity.setSupportActionBar(mainToolbar)
    }
}