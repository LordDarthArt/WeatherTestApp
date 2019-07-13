package tk.lorddarthart.weathertest.application.view.activity

import android.os.Bundle
import android.view.Menu
import tk.lorddarthart.weathertest.R
import tk.lorddarthart.weathertest.application.view.base.BaseActivity
import androidx.fragment.app.FragmentManager
import tk.lorddarthart.weathertest.application.view.fragment.MainFragment

class MainActivity : BaseActivity() {

    private var mainFragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = R.id.mainFragment

        supportFragmentManager.beginTransaction()
                .replace(mainFragment, MainFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_setcity, menu)
        return super.onCreateOptionsMenu(menu)
    }
}