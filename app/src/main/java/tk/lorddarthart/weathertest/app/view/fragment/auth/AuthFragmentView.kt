package tk.lorddarthart.weathertest.app.view.fragment.auth

import com.arellomobile.mvp.MvpView

interface AuthFragmentView : MvpView {

    fun showErrorToUser(text: String)

    fun setError(errorText: String)

}