//package tk.lorddarthart.weathertest.application.view.adapter.pager
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import tk.lorddarthart.weathertest.application.view.fragment.pages.ExtendedFragmentSevenDaysPage
//import tk.lorddarthart.weathertest.application.view.fragment.pages.ExtendedFragmentTodayPage
//import tk.lorddarthart.weathertest.application.view.fragment.pages.ExtendedFragmentTomorrowPage
//
//class PagerAdapter(
//        fragmentManager: FragmentManager,
//        private val numberOfTabs: Int
//) : FragmentPagerAdapter(fragmentManager) {
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> ExtendedFragmentTodayPage()
//            1 -> ExtendedFragmentTomorrowPage()
//            2 -> ExtendedFragmentSevenDaysPage()
//            else -> ExtendedFragmentTodayPage()
//        }
//    }
//
//    override fun getCount(): Int {
//        return numberOfTabs
//    }
//}