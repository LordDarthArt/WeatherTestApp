package tk.lorddarthart.weathertest.application.view.activity

import android.os.Bundle
import android.view.Menu
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_setcity, menu)
        return super.onCreateOptionsMenu(menu)
    }
}