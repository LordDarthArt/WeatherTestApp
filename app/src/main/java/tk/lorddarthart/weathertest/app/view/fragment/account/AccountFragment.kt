package tk.lorddarthart.weathertest.app.view.fragment.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import tk.lorddarthart.weathertest.app.Screens
import tk.lorddarthart.weathertest.app.presenter.activity.MainActivityPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.account.AccountFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAccountBinding
import tk.lorddarthart.weathertest.util.helper.IOnBackPressed
import javax.inject.Inject

/**
 * Created by LordDarthArt on 26.10.2019.
 */
class AccountFragment : BaseFragment(), AccountFragmentView, IOnBackPressed {
    private lateinit var fragmentBinding: FragmentAccountBinding

    @Inject
    @InjectPresenter
    lateinit var accountPresenter: AccountFragmentPresenter

    @ProvidePresenter
    fun provideAccountPresenter(): AccountFragmentPresenter = accountPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentBinding = FragmentAccountBinding.inflate(inflater, container, false)

        initialization()
        setContent()

        return fragmentBinding.root
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
        router.replaceScreen(Screens.CitiesListScreen)
        return true
    }
}