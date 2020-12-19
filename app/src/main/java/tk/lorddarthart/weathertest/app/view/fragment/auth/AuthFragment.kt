package tk.lorddarthart.weathertest.app.view.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.weathertest.app.presenter.fragment.account.AccountFragmentPresenter
import tk.lorddarthart.weathertest.app.presenter.fragment.auth.AuthFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAuthBinding
import javax.inject.Inject

class AuthFragment: BaseFragment(), AuthFragmentView {
    private lateinit var fragmentBinding: FragmentAuthBinding

    @Inject
    @InjectPresenter
    lateinit var authPresenter: AuthFragmentPresenter

    @ProvidePresenter
    fun provideAuthPresenter(): AuthFragmentPresenter = authPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentBinding = FragmentAuthBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return fragmentBinding.fragmentAuthRoot
    }

    override fun initViews() {
        // do something
    }

    override fun initListeners() {
        with(fragmentBinding) {
            fragmentAuthSignInButton.setOnClickListener {
                authPresenter.signIn(
                        fragmentAuthEmail.text.toString(), fragmentAuthPassword.text.toString()
                )
            }
        }
    }

    override fun setContent() {
        // do something
    }

    override fun showErrorToUser(text: String) {
        fragmentBinding.root.longSnackbar(text).show()
    }

    override fun setError(errorText: String) {
        // TODO: do something
    }
}