package tk.lorddarthart.weathertest.app.view.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.design.longSnackbar
import tk.lorddarthart.presenter.fragment.auth.AuthFragmentPresenter
import tk.lorddarthart.presenter.fragment.auth.AuthFragmentView
import tk.lorddarthart.utils.helper.logDebug
import tk.lorddarthart.utils.helper.logError
import tk.lorddarthart.weathertest.app.WeatherTestApp
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentAuthBinding
import javax.inject.Inject

class AuthFragment: BaseFragment(), AuthFragmentView {
    private lateinit var fragmentBinding: FragmentAuthBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

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

    override fun performAuth(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    logDebug("signInWithEmail:success")
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        WeatherTestApp.setUser(user)
                        mainActivity.mainActivityPresenter.checkAuthorization()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    logError( "signInWithEmail:failure", task.exception)
                    showErrorToUser("Authentication failed")
                    mainActivity.mainActivityPresenter.checkAuthorization()
                }

                // ...
            }
    }
}