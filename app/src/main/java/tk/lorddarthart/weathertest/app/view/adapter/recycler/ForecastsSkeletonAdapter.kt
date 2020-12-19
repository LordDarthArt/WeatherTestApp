package tk.lorddarthart.weathertest.app.view.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.weathertest.databinding.SingleItemForecastSkeletonBinding


class ForecastsSkeletonAdapter(
    private val skeletonSize: Int
) : RecyclerView.Adapter<ForecastsSkeletonAdapter.ForecastsSkeletonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastsSkeletonHolder {
        val itemBinding = SingleItemForecastSkeletonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastsSkeletonHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForecastsSkeletonHolder, position: Int) {
        holder.bind(position)
    }

    inner class ForecastsSkeletonHolder(private val itemBinding: SingleItemForecastSkeletonBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(position: Int) {
            // just bind this
        }
    }

    override fun getItemCount(): Int {
        return skeletonSize
    }
}