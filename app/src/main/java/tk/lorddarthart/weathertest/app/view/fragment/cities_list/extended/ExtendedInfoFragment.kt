package tk.lorddarthart.weathertest.app.view.fragment.cities_list.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.weathertest.app.view.adapter.pager.PagerAdapter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.app.view.fragment.pages.general.ExtendedFragmentGeneral
import tk.lorddarthart.weathertest.app.view.fragment.pages.hourly.ExtendedFragmentHourly
import tk.lorddarthart.weathertest.databinding.FragmentExtendedBinding

class ExtendedInfoFragment : BaseFragment(), ExtendedInfoFragmentView {

    private lateinit var fragmentBinding: FragmentExtendedBinding
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = FragmentExtendedBinding.inflate(inflater, container, false)

        setContent()

        return fragmentBinding.root
    }

    override fun initViews() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    override fun setContent() {
        fragmentBinding.fragmentExtendedTabLayout.tabGravity = TabLayout.GRAVITY_FILL

        pagerAdapter = PagerAdapter(this, listOf(ExtendedFragmentGeneral(), ExtendedFragmentHourly()))
        fragmentBinding.fragmentExtendedViewPager.adapter = pagerAdapter
    }
}
