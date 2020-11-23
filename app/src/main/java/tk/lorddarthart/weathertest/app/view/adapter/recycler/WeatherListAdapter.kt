package tk.lorddarthart.weathertest.app.view.adapter.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.app.model.entities.ForecastEntity
import tk.lorddarthart.weathertest.app.view.adapter.recycler.WeatherListAdapter.WeatherCityViewHolder
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT
import tk.lorddarthart.weathertest.util.converter.SimpleWeatherPlusMinusConverter.getPlusInFront
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter(
    private val onItemTouchListener: OnItemTouchListener
) : RecyclerView.Adapter<WeatherCityViewHolder>() {
    private lateinit var view: View
    private lateinit var viewHolder: WeatherCityViewHolder

    val differ = AsyncListDiffer(this, SameCallback<ForecastEntity>())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCityViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_forecast, parent, false)
        viewHolder = WeatherCityViewHolder(view)
        return viewHolder
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: WeatherCityViewHolder, position: Int) {
        val weather = differ.currentList[position]
        holder.textViewCity.text = weather.weatherCity
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
        val date = Date(weather.weatherDate)
        holder.textViewToday.text = sdf.format(date)
        holder.textViewHighLow.text = "▲ ${getPlusInFront(weather.weatherHigh)} ▼ ${weather.weatherLow}"
        holder.textViewTemperature.text = getPlusInFront(weather.weatherNow)
        holder.textViewSunTime.text = "✺▲ ${weather.weatherSunrise} ✺▼ ${weather.weatherSunset}"
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class WeatherCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewCity: TextView = itemView.findViewById(R.id.tvCity)
        internal var textViewToday: TextView = itemView.findViewById(R.id.tvDate)
        internal var textViewTemperature: TextView = itemView.findViewById(R.id.tvTemp)
        internal var textViewSunTime: TextView = itemView.findViewById(R.id.tvSuntime)
        internal var textViewHighLow: TextView = itemView.findViewById(R.id.tvHighlow)

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, adapterPosition) }
        }
    }
}