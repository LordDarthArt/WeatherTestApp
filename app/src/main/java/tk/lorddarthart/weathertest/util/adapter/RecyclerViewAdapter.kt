package tk.lorddarthart.weathertest.util.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tk.lorddarthart.weathertest.OnItemTouchListener
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.Weather
import tk.lorddarthart.weathertest.util.constant.Format.SIMPLE_DATE_FORMAT

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class RecyclerViewAdapter(
        private var context: Context,
        private var listWeather: ArrayList<Weather>,
        private val onItemTouchListener: OnItemTouchListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var view: View
    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = listWeather[position]
        holder.tvCity.text = weather.weather_city
        val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
        val date = Date(weather.weather_date)
        holder.tvToday.text = sdf.format(date)
        if (weather.weather_high > 0) {
            holder.tvHighLow.text = "▲ +" + weather.weather_high.toString()
        } else {
            holder.tvHighLow.text = "▲ " + weather.weather_high.toString()
        }
        if (weather.weather_low > 0) {
            holder.tvHighLow.text = holder.tvHighLow.text.toString() + " ▼ +" + weather.weather_low.toString()
        } else {
            holder.tvHighLow.text = holder.tvHighLow.text.toString() + " ▼ " + weather.weather_low.toString()
        }
        if (weather.weather_now > 0) {
            holder.tvCelsius.text = "+" + weather.weather_now.toString()
        } else {
            holder.tvCelsius.text = weather.weather_now.toString()
        }
        holder.tvSuntime.text = "✺▲ " + weather.weather_sunrise + " ✺▼ " + weather.weather_sunset
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvCity: TextView = itemView.findViewById(R.id.tvCity)
        internal var tvToday: TextView = itemView.findViewById(R.id.tvDate)
        internal var tvCelsius: TextView = itemView.findViewById(R.id.tvTemp)
        internal var tvSuntime: TextView = itemView.findViewById(R.id.tvSuntime)
        internal var tvHighLow: TextView = itemView.findViewById(R.id.tvHighlow)

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, layoutPosition) }
        }
    }
}