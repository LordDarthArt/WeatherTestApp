package tk.lorddarthart.weathertest.application;

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import tk.lorddarthart.weathertest.application.view.fragment.extended.ExtendedInfoFragment
import tk.lorddarthart.weathertest.application.view.fragment.main.MainFragment

object Screens {
    object MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainFragment()
        }
    }

    object ExtendedInfoScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ExtendedInfoFragment()
        }
    }
}