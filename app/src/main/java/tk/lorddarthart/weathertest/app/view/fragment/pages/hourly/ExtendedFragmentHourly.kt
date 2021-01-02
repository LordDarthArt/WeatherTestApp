package tk.lorddarthart.weathertest.app.view.fragment.pages.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.presenter.InjectPresenter
import tk.lorddarthart.presenter.fragment.page.hourly.ExtendedFragmentHourlyPresenter
import tk.lorddarthart.presenter.fragment.page.hourly.ExtendedFragmentHourlyView
import tk.lorddarthart.weathertest.app.view.base.fragment.pages.BasePageFragment
import tk.lorddarthart.weathertest.databinding.FragmentExtendedHourlyBinding

/**
 * Created by LordDarthArt at 25.09.2019
 */
class ExtendedFragmentHourly : BasePageFragment(), ExtendedFragmentHourlyView {

    private lateinit var binding: FragmentExtendedHourlyBinding

    @InjectPresenter
    lateinit var extendedFragmentHourlyPresenter: ExtendedFragmentHourlyPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExtendedHourlyBinding.inflate(inflater, container, false)

        return binding.root
    }
}
