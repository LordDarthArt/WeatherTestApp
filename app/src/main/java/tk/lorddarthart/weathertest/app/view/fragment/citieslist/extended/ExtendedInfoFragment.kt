package tk.lorddarthart.weathertest.app.view.fragment.citieslist.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.weathertest.app.view.adapter.pager.PagerAdapter
import tk.lorddarthart.weathertest.app.view.fragment.pages.hourly.ExtendedFragmentHourly
import tk.lorddarthart.weathertest.databinding.FragmentExtendedBinding
import tk.lorddarthart.weathertest.databinding.FragmentExtendedGeneralBinding

class ExtendedInfoFragment : BottomSheetDialogFragment(), ExtendedInfoFragmentView {

    private lateinit var fragmentBinding: FragmentExtendedGeneralBinding
    private val pagerAdapter: PagerAdapter by lazy {
        PagerAdapter(this, listOf(ExtendedFragmentHourly()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentBinding = FragmentExtendedGeneralBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent()
    }

    private fun setContent() {

    }

    private fun initListeners() {
        // do nothing
    }
}
