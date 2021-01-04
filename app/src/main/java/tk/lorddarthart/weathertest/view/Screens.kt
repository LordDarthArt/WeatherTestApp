package tk.lorddarthart.weathertest.view;

import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import tk.lorddarthart.weathertest.view.fragment.account.AccountFragment
import tk.lorddarthart.weathertest.view.fragment.auth.AuthFragment
import tk.lorddarthart.weathertest.view.fragment.citieslist.CitiesListFragment
import tk.lorddarthart.weathertest.view.fragment.pages.general.ExtendedFragmentGeneral

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
        override fun getFragment(): BottomSheetDialogFragment {
            return ExtendedFragmentGeneral()
        }
    }

    object AccountScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AccountFragment()
        }
    }
}