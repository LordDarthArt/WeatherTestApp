package tk.lorddarthart.weathertest.view.adapter.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tk.lorddarthart.weathertest.view.base.fragment.pages.BasePageFragment

class PagerAdapter(
    holderFragment: Fragment,
    private val fragmentsList: List<BasePageFragment>
) : FragmentStateAdapter(holderFragment) {
    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}