package tk.lorddarthart.weathertest.app;

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import tk.lorddarthart.weathertest.app.view.fragment.account.AccountFragment
import tk.lorddarthart.weathertest.app.view.fragment.auth.AuthFragment
import tk.lorddarthart.weathertest.app.view.fragment.cities_list.CitiesListFragment
import tk.lorddarthart.weathertest.app.view.fragment.cities_list.extended.ExtendedInfoFragment

object Screens {
    object AuthScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AuthFragment()
        }
    }

    object CitiesListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return CitiesListFragment()
        }
    }

    object ExtendedInfoScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ExtendedInfoFragment()
        }
    }

    object AccountScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AccountFragment()
        }
    }
}