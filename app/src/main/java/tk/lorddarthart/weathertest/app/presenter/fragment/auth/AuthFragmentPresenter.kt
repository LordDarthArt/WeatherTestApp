package tk.lorddarthart.weathertest.app.presenter.fragment.auth

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import tk.lorddarthart.weathertest.app.App
import tk.lorddarthart.weathertest.app.view.fragment.auth.AuthFragment
import tk.lorddarthart.weathertest.app.view.fragment.auth.AuthFragmentView
import tk.lorddarthart.weathertest.util.extension.isThisEmail
import tk.lorddarthart.weathertest.util.extension.isThisPassword
import tk.lorddarthart.weathertest.util.helper.logDebug
import tk.lorddarthart.weathertest.util.helper.logError

@InjectViewState
class AuthFragmentPresenter: MvpPresenter<AuthFragmentView>() {
    private val auth = FirebaseAuth.getInstance()

     fun signIn(email: String, password: String) {
         when {
             !email.isBlank() && !password.isBlank() -> {
                 auth.signInWithEmailAndPassword(email, password)
                         .addOnCompleteListener((viewState as AuthFragment).mainActivity) { task ->
                             if (task.isSuccessful) {
                                 // Sign in success, update UI with the signed-in user's information
                                 logDebug(this@AuthFragmentPresenter, "signInWithEmail:success")
                                 val user = auth.currentUser
                                 if (user != null && user.isEmailVerified) {
                                     App.setUser(user)
                                     (viewState as AuthFragment).mainActivity
                                             .mainActivityPresenter.checkAuthorization()
                                 }
                             } else {
                                 // If sign in fails, display a message to the user.
                                 logError(this@AuthFragmentPresenter, "signInWithEmail:failure", task.exception)
                                 viewState.showErrorToUser("Authentication failed")
                                 (viewState as AuthFragment).mainActivity
                                         .mainActivityPresenter.checkAuthorization()
                             }

                             // ...
                         }
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