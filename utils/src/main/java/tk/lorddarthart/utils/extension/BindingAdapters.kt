package tk.lorddarthart.utils.extension

import android.view.View
import androidx.databinding.BindingAdapter

class BindingAdapters {
    @BindingAdapter("app:visibility")
    fun setVisibility(view: View, visible: Boolean) {
        view.setVisibility { visible }
    }
}