package tk.lorddarthart.weathertest.application.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.model.forecast.current.CurrentForecast
import tk.lorddarthart.weathertest.util.OnItemTouchListener
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT
import tk.lorddarthart.weathertest.util.converter.SimpleWeatherPlusMinusConverter.getPlusInFront
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(
        private var context: Context,
        private var listWeather: ArrayList<CurrentForecast>,
        private val onItemTouchListener: OnItemTouchListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var view: View
    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(context).inflate(R.layout.single_item_forecast, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = listWeather[position]
        holder.textViewCity.text = weather.name
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
        val date = Date(weather.dt)
        holder.textViewToday.text = sdf.format(date)
        holder.textViewHighLow.text = "▲ ${getPlusInFront(weather.main.temp_max)} ▼ ${weather.main.temp_min}"
        holder.textViewTemperature.text = getPlusInFront(weather.main.temp)
        holder.textViewSunTime.text = "✺▲ ${weather.sys.sunrise} ✺▼ ${weather.sys.sunset}"
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewCity: TextView = itemView.findViewById(R.id.tvCity)
        internal var textViewToday: TextView = itemView.findViewById(R.id.tvDate)
        internal var textViewTemperature: TextView = itemView.findViewById(R.id.tvTemp)
        internal var textViewSunTime: TextView = itemView.findViewById(R.id.tvSuntime)
        internal var textViewHighLow: TextView = itemView.findViewById(R.id.tvHighlow)

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, layoutPosition) }
        }
    }
}