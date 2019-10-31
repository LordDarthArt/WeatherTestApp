package tk.lorddarthart.weathertest.app.view.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.weathertest.app.presenter.fragment.auth.AuthFragmentPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAuthBinding

class AuthFragment: BaseFragment(), AuthFragmentView {
    private lateinit var authFragmentBinding: FragmentAuthBinding

    @InjectPresenter
    lateinit var authFragmentPresenter: AuthFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        authFragmentBinding = FragmentAuthBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return authFragmentBinding.fragmentAuthRoot
    }

    override fun initViews() {
        // do something
    }

    override fun initListeners() {
        with(authFragmentBinding) {
            fragmentAuthSignInButton.setOnClickListener {
                authFragmentPresenter.signIn(
                        fragmentAuthEmail.text.toString(), fragmentAuthPassword.text.toString()
                )
            }
        }
    }

    override fun setContent() {
        // do something
    }

    override fun showErrorToUser(text: String) {
        authFragmentBinding.root.longSnackbar(text).show()
    }

    override fun setError(errorText: String) {
        // TODO: do something
    }
}