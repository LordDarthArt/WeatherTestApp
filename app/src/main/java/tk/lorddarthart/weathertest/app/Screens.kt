package tk.lorddarthart.weathertest.app;

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import tk.lorddarthart.weathertest.app.view.fragment.account.AccountFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.extended.ExtendedInfoFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.MainFragment
import tk.lorddarthart.weathertest.app.view.fragment.main.cities_list.CitiesListFragment

object Screens {
    object MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainFragment()
        }

        object CitiesListScreen: SupportAppScreen() {
            override fun getFragment(): Fragment {
                return CitiesListFragment()
            }
        }

        object ExtendedInfoScreen: SupportAppScreen() {
            override fun getFragment(): Fragment {
                return ExtendedInfoFragment()
            }
        }
    }

    object AccountScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AccountFragment()
        }
    }
}