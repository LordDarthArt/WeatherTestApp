package tk.lorddarthart.weathertest.view.fragment.citieslist.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity
import tk.lorddarthart.presenter.fragment.cities_list.extended.ExtendedInfoFragmentPresenter
import tk.lorddarthart.presenter.fragment.cities_list.extended.ExtendedInfoFragmentView
import tk.lorddarthart.weathertest.view.adapter.pager.PagerAdapter
import tk.lorddarthart.weathertest.view.fragment.pages.hourly.ExtendedFragmentHourly
import tk.lorddarthart.weathertest.databinding.FragmentExtendedGeneralBinding
import tk.lorddarthart.weathertest.view.base.fragment.DaggerBottomSheetDialogFragment
import javax.inject.Inject

class ExtendedInfoFragment : DaggerBottomSheetDialogFragment(), ExtendedInfoFragmentView {

    private lateinit var binding: FragmentExtendedGeneralBinding

    @Inject
    @InjectPresenter
    lateinit var presenter: ExtendedInfoFragmentPresenter

    @ProvidePresenter
    fun provideExtendedInfoPresenter(): ExtendedInfoFragmentPresenter = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExtendedGeneralBinding.inflate(inflater, container, false)
        if (presenter.cityForecast == null) {
            presenter.cityForecast = arguments?.get("forecast") as ForecastEntity?
        }
        binding.item = presenter.cityForecast
        return binding.root
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
