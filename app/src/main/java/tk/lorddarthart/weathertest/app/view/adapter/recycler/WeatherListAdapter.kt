package tk.lorddarthart.weathertest.app.view.adapter.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.view.adapter.recycler.WeatherListAdapter.WeatherCityViewHolder
import tk.lorddarthart.weathertest.databinding.SingleItemForecastBinding
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT_DAYTIME
import tk.lorddarthart.weathertest.util.converter.SimpleWeatherPlusMinusConverter.getPlusInFront
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherListAdapter(
    private val onItemTouchListener: OnItemTouchListener
) : ListAdapter<ForecastEntity, WeatherCityViewHolder>(SameCallback<ForecastEntity>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCityViewHolder {
        val itemBinding = SingleItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherCityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherCityViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class WeatherCityViewHolder(private val itemBinding: SingleItemForecastBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, adapterPosition) }
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(position: Int) {
            val forecastItem = currentList[position]

            itemBinding.apply {
                city.text = forecastItem.weatherCity
                date.text = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault()).format(forecastItem.weatherDate * 1000)
                highlow.text = "▲ ${getPlusInFront(forecastItem.weatherHigh.roundToInt())} ▼ ${getPlusInFront(forecastItem.weatherLow.roundToInt())}"
                temperature.text = getPlusInFront(forecastItem.weatherNow.roundToInt())
                suntime.text = "✺▲ ${SimpleDateFormat(SIMPLE_DATE_FORMAT_DAYTIME, Locale.getDefault()).format(forecastItem.weatherSunrise * 1000)} ✺▼ ${SimpleDateFormat(SIMPLE_DATE_FORMAT_DAYTIME).format(forecastItem.weatherSunset * 1000)}"
            }
        }
    }
}