package tk.lorddarthart.weathertest.app.view.fragment.pages

import android.annotation.SuppressLint
import tk.lorddarthart.weathertest.app.view.base.fragment.BaseFragment
import tk.lorddarthart.weathertest.databinding.PageTodayBinding
import tk.lorddarthart.weathertest.util.constant.Format
import java.text.SimpleDateFormat

open class BasePageFragment : BaseFragment() {
    override fun initViews() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    protected lateinit var dataBinding: PageTodayBinding
    protected lateinit var simpleDateFormat: SimpleDateFormat

    @SuppressLint("SimpleDateFormat")
    override fun setContent() {
        simpleDateFormat = SimpleDateFormat(Format.SIMPLE_DATE_FORMAT)
    }
}