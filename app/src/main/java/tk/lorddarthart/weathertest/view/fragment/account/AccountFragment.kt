package tk.lorddarthart.weathertest.view.fragment.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import tk.lorddarthart.weathertest.view.Screens
import tk.lorddarthart.presenter.fragment.account.AccountFragmentPresenter
import tk.lorddarthart.presenter.fragment.account.AccountFragmentView
import tk.lorddarthart.weathertest.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAccountBinding
import javax.inject.Inject

/**
 * Created by LordDarthArt on 26.10.2019.
 */
class AccountFragment : BaseFragment(), AccountFragmentView, tk.lorddarthart.utils.helper.IOnBackPressed {
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