package tk.lorddarthart.weathertest.application.view.fragment.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BasePageFragment

/**
 * A simple [Fragment] subclass.
 */
class ExtendedFragmentSevenDaysPage : BasePageFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_extended_fragment_seven_days_page, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
        super.initViews()
    }

    override fun setContent() {
        super.setContent()
    }
}
