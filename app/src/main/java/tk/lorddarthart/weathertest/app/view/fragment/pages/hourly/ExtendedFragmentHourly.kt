package tk.lorddarthart.weathertest.app.view.fragment.pages.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arellomobile.mvp.presenter.InjectPresenter
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.presenter.fragment.page.hourly.ExtendedFragmentHourlyPresenter
import tk.lorddarthart.weathertest.app.view.base.fragment.pages.BasePageFragment
import tk.lorddarthart.weathertest.databinding.FragmentExtendedHourlyBinding

/**
 * Created by LordDarthArt at 25.09.2019
 */
class ExtendedFragmentHourly : BasePageFragment(), ExtendedFragmentHourlyView {

    lateinit var extendedFragmentHourlyBinding: FragmentExtendedHourlyBinding

    @InjectPresenter
    lateinit var extendedFragmentHourlyPresenter: ExtendedFragmentHourlyPresenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        extendedFragmentHourlyBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_extended_hourly,
                container,
                false
        )

        return extendedFragmentHourlyBinding.root
    }
}
