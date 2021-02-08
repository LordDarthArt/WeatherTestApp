package tk.lorddarthart.weathertest.view.adapter.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.data.local.forecast.entity.ForecastEntity
import tk.lorddarthart.weathertest.view.adapter.recycler.ForecastsAdapter.WeatherCityViewHolder
import tk.lorddarthart.weathertest.databinding.SingleItemForecastBinding
import tk.lorddarthart.utils.helper.OnItemTouchListener
import tk.lorddarthart.utils.converter.SimpleWeatherPlusMinusConverter.getPlusInFront
import java.text.DateFormat
import kotlin.math.roundToInt

class ForecastsAdapter(
    private val onTap: (ForecastEntity) -> Unit
) : ListAdapter<ForecastEntity, WeatherCityViewHolder>(SameCallback<ForecastEntity>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCityViewHolder {
        val itemBinding = SingleItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherCityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherCityViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class WeatherCityViewHolder(private val itemBinding: SingleItemForecastBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(position: Int) {
            val forecastItem = currentList[position]

            itemBinding.apply {
                city.text = forecastItem.weatherCity
                date.text = DateFormat.getDateInstance(DateFormat.LONG).format(forecastItem.weatherDate.toInt() * 1000)
                high.text = "${getPlusInFront(forecastItem.weatherHigh.toDouble().roundToInt())}°"
                low.text = "${getPlusInFront(forecastItem.weatherLow.toDouble().roundToInt())}°"
                temperature.text = "${getPlusInFront(forecastItem.weatherNow.toDouble().roundToInt())}°"
                sunrise.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(forecastItem.weatherSunrise.toInt() * 1000)
                sunset.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(forecastItem.weatherSunset.toInt() * 1000)

                root.setOnClickListener { onTap(currentList[position]) }
            }
        }
    }
}