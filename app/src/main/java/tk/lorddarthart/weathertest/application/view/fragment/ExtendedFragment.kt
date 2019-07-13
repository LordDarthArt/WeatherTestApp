package tk.lorddarthart.weathertest.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_extended.view.*
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BaseFragment

class ExtendedFragment : BaseFragment() {
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_extended, container, false)

        initViews()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            viewPager = extended_fragment_view_pager
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExtendedFragment()
    }
}
