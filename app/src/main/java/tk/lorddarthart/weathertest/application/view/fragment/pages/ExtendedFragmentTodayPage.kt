package tk.lorddarthart.weathertest.application.view.fragment.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.databinding.PageTodayBinding

class ExtendedFragmentTodayPage : BasePageFragment() {

    lateinit var extendedFragmentTodayPageBinding: PageTodayBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        extendedFragmentTodayPageBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.page_today,
                container,
                false
        )

        return extendedFragmentTodayPageBinding.root
    }
}
