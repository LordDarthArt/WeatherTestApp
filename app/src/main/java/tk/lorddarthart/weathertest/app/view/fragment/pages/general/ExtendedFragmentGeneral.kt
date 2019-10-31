package tk.lorddarthart.weathertest.app.view.fragment.pages.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tk.lorddarthart.weathertest.app.view.base.fragment.pages.BasePageFragment
import tk.lorddarthart.weathertest.databinding.FragmentExtendedGeneralBinding

/**
 * Created by LordDarthArt at 25.09.2019
 */
class ExtendedFragmentGeneral : BasePageFragment(), ExtendedFragmentGeneralView {

    private lateinit var extendedFragmentGeneralBinding: FragmentExtendedGeneralBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        extendedFragmentGeneralBinding = FragmentExtendedGeneralBinding.inflate(
                inflater,
                container,
                false
        )

        initialization()
        setContent()

        return extendedFragmentGeneralBinding.root
    }

    override fun setContent() {
        super.setContent()
    }
}
