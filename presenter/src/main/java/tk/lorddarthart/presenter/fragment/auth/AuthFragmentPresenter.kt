package tk.lorddarthart.presenter.fragment.auth

import moxy.InjectViewState
import moxy.MvpPresenter
import tk.lorddarthart.utils.extension.isThisEmail
import tk.lorddarthart.utils.extension.isThisPassword

@InjectViewState
class AuthFragmentPresenter : MvpPresenter<AuthFragmentView>() {

    fun signIn(email: String, password: String) {
        when {
            !email.isBlank() && !password.isBlank() -> {
                viewState.performAuth(email, password)
            }
            !email.isThisEmail() -> {
                viewState.setError("")
            }
            !password.isThisPassword() -> {
                viewState.setError("")
            }
        }
    }
}