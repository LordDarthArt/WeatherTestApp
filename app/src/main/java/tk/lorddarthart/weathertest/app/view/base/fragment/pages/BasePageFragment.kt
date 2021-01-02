package tk.lorddarthart.weathertest.app.view.base.fragment.pages

import android.annotation.SuppressLint
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.utils.constant.Format
import java.text.SimpleDateFormat

open class BasePageFragment : BaseFragment() {
    override fun initViews() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    protected lateinit var simpleDateFormat: SimpleDateFormat

    @SuppressLint("SimpleDateFormat")
    override fun setContent() {
        simpleDateFormat = SimpleDateFormat(tk.lorddarthart.utils.constant.Format.SIMPLE_DATE_FORMAT)
    }
}