package tk.lorddarthart.weathertest.app.view.adapter.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SameCallback<T: Any>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
       return oldItem == newItem
    }
}