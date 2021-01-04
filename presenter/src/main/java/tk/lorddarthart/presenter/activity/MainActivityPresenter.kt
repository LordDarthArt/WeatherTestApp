package tk.lorddarthart.presenter.activity

import moxy.InjectViewState
import moxy.MvpPresenter

/** Created by LordDarthArt at 23.09.2019. */
@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    fun initMainFragment() {
        initVariables()
        checkAuthorization()
    }

    private fun initVariables() {
        viewState.initVariables()
    }

    fun checkAuthorization() {
        viewState.checkAuthorization()
    }

    fun showExitDialog() {
        viewState.showExitDialog()
    }
}