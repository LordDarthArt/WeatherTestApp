package tk.lorddarthart.weathertest.app.view.fragment.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.databinding.FragmentExtendedFragmentSevenDaysPageBinding

/**
 * A simple [Fragment] subclass.
 */
class ExtendedFragmentSevenDaysPage : BasePageFragment() {
    lateinit var extendedFragmentSevenDaysPageBinding: FragmentExtendedFragmentSevenDaysPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        extendedFragmentSevenDaysPageBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_extended_fragment_seven_days_page,
                container,
                false
        )

        return extendedFragmentSevenDaysPageBinding.root
    }

    override fun setContent() {
        super.setContent()
    }
}
