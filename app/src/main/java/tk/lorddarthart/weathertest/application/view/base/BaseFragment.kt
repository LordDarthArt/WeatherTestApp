package tk.lorddarthart.weathertest.application.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tk.lorddarthart.weathertest.application.view.activity.MainActivity

open class BaseFragment : Fragment() {
    protected lateinit var mainView: View
    protected lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initViews()
        setContent()

        return mainView
    }

    open fun initViews() {

    }

    open fun setContent() {

    }
}