package tk.lorddarthart.weathertest.application.view.fragment.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.application.view.adapter.pager.PagerAdapter
import tk.lorddarthart.weathertest.databinding.FragmentExtendedBinding

class ExtendedInfoFragment : BaseFragment() {
    override fun initViews() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    private lateinit var dataBinding: FragmentExtendedBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_extended,
                container,
                false
        )

        setContent()

        return dataBinding.root
    }

    override fun setContent() {
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        pagerAdapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExtendedInfoFragment()
    }
}
