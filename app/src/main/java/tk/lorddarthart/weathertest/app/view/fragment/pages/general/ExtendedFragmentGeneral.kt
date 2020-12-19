package tk.lorddarthart.weathertest.app.view.fragment.pages.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tk.lorddarthart.weathertest.app.view.base.fragment.pages.BasePageFragment
import tk.lorddarthart.weathertest.databinding.FragmentExtendedGeneralBinding

/**
 * Created by LordDarthArt at 25.09.2019
 */
class ExtendedFragmentGeneral : BottomSheetDialogFragment(), ExtendedFragmentGeneralView {
    private lateinit var fragmentBinding: FragmentExtendedGeneralBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        fragmentBinding = FragmentExtendedGeneralBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }
}
