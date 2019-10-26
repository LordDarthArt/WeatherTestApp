package tk.lorddarthart.weathertest.app.view.fragment.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAccountBinding
import tk.lorddarthart.weathertest.util.helper.IOnBackPressed


/**
 * Created by LordDarthArt on 26.10.2019.
 */
class AccountFragment: BaseFragment(), AccountFragmentView, IOnBackPressed {

    lateinit var accountFragmentBinding: FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        accountFragmentBinding = FragmentAccountBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return accountFragmentBinding.root
    }

    override fun initialization() {
        super.initialization()
    }

    override fun initViews() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun setContent() {
        // do something
    }

    override fun onBackPressed(): Boolean {
        router.replaceScreen(Screens.MainScreen)
        return true
    }
}