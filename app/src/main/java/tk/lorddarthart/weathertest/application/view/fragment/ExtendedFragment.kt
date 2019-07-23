package tk.lorddarthart.weathertest.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_extended.view.*
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BaseFragment
import tk.lorddarthart.weathertest.application.view.adapter.pager.PagerAdapter

class ExtendedFragment : BaseFragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_extended, container, false)

        initViews()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            tabLayout = fragment_extended_tab_layout
            viewPager = fragment_extended_view_pager
        }
    }

    override fun setContent() {
        super.setContent()

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        pagerAdapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExtendedFragment()
    }
}
