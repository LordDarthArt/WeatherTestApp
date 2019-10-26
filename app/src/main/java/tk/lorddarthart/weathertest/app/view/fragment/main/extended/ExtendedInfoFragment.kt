package tk.lorddarthart.weathertest.app.view.fragment.main.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.weathertest.app.view.adapter.pager.PagerAdapter
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.FragmentExtendedBinding

class ExtendedInfoFragment : BaseFragment(), ExtendedInfoFragmentView {
    override fun initViews() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    override var extendedInfoFragmentBinding: FragmentExtendedBinding? = null
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        extendedInfoFragmentBinding = FragmentExtendedBinding.inflate(
                inflater,
                container,
                false
        )

        setContent()

        return extendedInfoFragmentBinding!!.root
    }

    override fun setContent() {
        extendedInfoFragmentBinding?.fragmentExtendedTabLayout?.tabGravity = TabLayout.GRAVITY_FILL

//        pagerAdapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)
//        viewPager.adapter = pagerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExtendedInfoFragment()
    }
}
