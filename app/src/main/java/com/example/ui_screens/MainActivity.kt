package com.example.ui_screens

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity() : AppCompatActivity(), Parcelable {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout


    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        val pagerAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val navItemLeft = findViewById<ImageView>(R.id.navItemLeft)
        val navItemCenter = findViewById<ImageView>(R.id.navItemCenter)
        val navItemRight = findViewById<ImageView>(R.id.navItemRight)

        navItemLeft.setOnClickListener {
            viewPager.currentItem = 0 // Replace with the index of the desired fragment
        }

        navItemCenter.setOnClickListener {
            viewPager.currentItem = 1 // Replace with the index of the desired fragment
        }

        navItemRight.setOnClickListener {
            viewPager.currentItem = 2 // Replace with the index of the desired fragment
        }

        setTabIcons()

        val filterIcon = findViewById<ImageView>(R.id.filterIcon)
        filterIcon.setOnClickListener {
            // Start the new activity when the filter icon is clicked
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_item2 // Set item 2 as the default highlighted item
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_item1 -> {
                    val intent = Intent(this@MainActivity, RefineActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_item2 -> {
                    viewPager.currentItem = 4 // Replace with the index of the desired fragment
                    true
                }
                R.id.navigation_item3 -> {
                    // Handle navigation item 3
                    true
                }
                R.id.navigation_item4 -> {
                    // Handle navigation item 4
                    true
                }
                R.id.navigation_item5 -> {
                    // Handle navigation item 5
                    true
                }
                else -> false
            }
        }
    }

    private fun setTabIcons() {
        val tabIcons = listOf(
            R.drawable.people,
            R.drawable.briefcase,
            R.drawable.network,
        )

        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.customView = createTabView(tabIcons[i])
        }
    }

    private fun createTabView(imageResId: Int): View {
        val tabView = LayoutInflater.from(this).inflate(R.layout.tab_custom_view, null)
        val tabImageView = tabView.findViewById<ImageView>(R.id.tabImageView)
        tabImageView.setImageResource(imageResId)
        return tabView
    }

    class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private companion object {
            private const val NUM_TABS = 3 // Number of tabs
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CardFragment() // Replace CardFragment() with your actual fragment class
                1 -> CardFragment() // Replace CardFragment() with your actual fragment class
                2 -> CardFragment() // Replace CardFragment() with your actual fragment class
                else -> throw IllegalArgumentException("Invalid tab position: $position")
            }
        }


        override fun getCount(): Int {
            return NUM_TABS
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return null // Return null to set custom tab views instead of text titles
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit Confirmation")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
                finishAffinity() // Finish all activities and exit the application
            }
            .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .show()
    }


}
