package tk.lorddarthart.weathertest.app.view.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tk.lorddarthart.weathertest.app.view.fragment.pages.general.ExtendedFragmentGeneral
import tk.lorddarthart.weathertest.app.view.fragment.pages.hourly.ExtendedFragmentHourly

class PagerAdapter(
        fragmentManager: FragmentManager,
        private val numberOfTabs: Int
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ExtendedFragmentGeneral()
            1 -> ExtendedFragmentHourly()
            else -> ExtendedFragmentGeneral()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}