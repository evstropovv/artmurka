package com.artmurka.artmurkaapp.android.views.activities.main

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.android.views.activities.login.LoginActivity
import com.artmurka.artmurkaapp.android.views.fragments.FragmentCategoryChilds
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.android.views.fragments.DeliveryFragment
import com.artmurka.artmurkaapp.android.views.fragments.IndividualFragment
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.android.views.fragments.BasketFragment
import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.android.views.fragments.CategorySettings
import com.artmurka.artmurkaapp.android.views.fragments.ItemListFragment
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.CategoryFragment
import com.artmurka.artmurkaapp.android.views.fragments.OrderFragment
import com.artmurka.artmurkaapp.android.views.fragments.WishFragment

import java.util.ArrayList

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector


class MainActivity : AppCompatActivity(), IMainActivity, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    private var tvBigName: TextView? = null
    private var tvSmallName: TextView? = null
    private var fragCategory: CategoryFragment? = null
    private val TAG = "Storage_category_fragment"
    private var btnLogin: Button? = null
    internal var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Preferences.init(this)
        loadShopFragment()
        setUI()

        runtimePermission()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                tvBigName?.text = data?.extras!!.getString("name")
                tvSmallName?.text = data?.extras!!.getString("email")
                btnLogin?.text = "Вийти"
                Preferences.isLogin = true
            }
        }
    }

    private fun loadShopFragment() {
        val fm = supportFragmentManager
        val fragment = fm.findFragmentByTag(TAG)
        if (fragment == null) {
            fragCategory = CategoryFragment()
            fm.beginTransaction()
                    .replace(R.id.mainFrame, fragCategory!!, TAG)
                    //without backstacktrace
                    .commitAllowingStateLoss()
            fm.executePendingTransactions()
        } else {
            fragCategory = fragment as CategoryFragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.card) {
            this.changeFragment(Const.BASKET_FRAGMENT)
        }
        if (item.itemId == R.id.sort) {
            this.changeFragment(Const.CATEGORY_SETTINGS_FRAGMENT)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUI() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val header = navigationView.getHeaderView(0)
        tvSmallName = header.findViewById<View>(R.id.tvSmallName) as TextView
        tvSmallName!!.text = Preferences.email
        tvBigName = header.findViewById<View>(R.id.tvBigName) as TextView
        tvBigName!!.text = Preferences.name
        btnLogin = header.findViewById<View>(R.id.btnLogin) as Button
        btnLogin!!.text = if (Preferences.isLogin!!) "Вийти" else "Увійти"
        btnLogin!!.setOnClickListener { v ->
            if (!Preferences.isLogin!!) {
                btnLogin!!.text = "Логін"
                val intent = Intent(v.context, LoginActivity::class.java)
                startActivityForResult(intent, 1)
            } else {
                val builder = AlertDialog.Builder(v.context)
                builder.setMessage(Preferences.name + ", ?? ???????? ?? ?????? ????? ? ????????")
                        .setPositiveButton("???") { dialog, id ->
                            Preferences.consumerKey = BuildConfig.CONSUMER_KEY
                            Preferences.consumerSecret = BuildConfig.CONSUMER_SECRET
                            Preferences.oauthToken = BuildConfig.OAUTH_TOKEN
                            Preferences.oauthTokenSecret = BuildConfig.OAUTH_TOKEN_SECRET
                            Preferences.name = "???-?????"
                            Preferences.email = "artmurka.com"
                            tvBigName!!.text = "???-?????"
                            tvSmallName!!.text = "artmurka.com"
                            Preferences.isLogin = false
                        }
                        .setNegativeButton("??") { dialog, id -> }
                builder.create().show()
            }
        }

        //????? ???????
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        //        super.onSaveInstanceState(outState, outPersistentState);
        //        if (fragCategory != null) {
        //            outState.putString(TAG, fragCategory.getTag());
        //        }
    }

    override fun changeFragment(fragment: Int, url: String? , childs: List<Success>? ,catName: String?) {
        val fm = supportFragmentManager
        val bundle = Bundle()
        when (fragment) {
            100 -> {
                val categoryChilds = FragmentCategoryChilds()

                bundle.putParcelableArrayList("childs", childs as ArrayList<out Parcelable>)
                bundle.putString("catName", catName)
                categoryChilds.arguments = bundle

                fm.beginTransaction()
                        .replace(R.id.mainFrame, categoryChilds)
                        .addToBackStack("categoryChilds")
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }

            101 -> {
                val categoryFragment = CategoryFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, categoryFragment)
                        .addToBackStack(TAG)
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }

            102 -> {
                val itemList = ItemListFragment()
                bundle.putString("url", url)
                itemList.arguments = bundle
                fm.beginTransaction()
                        .replace(R.id.mainFrame, itemList)
                        .addToBackStack("a")
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            103 -> {
                val cartFragment = BasketFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, cartFragment)
                        .addToBackStack("cartFragment")
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            104 -> {
                val wishFragment = WishFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, wishFragment)
                        .addToBackStack("wishFragment")
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            105 -> {
                val orderFragment = OrderFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, orderFragment)
                        .addToBackStack("orderFragment")
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            106 -> {
                val categorySettingsFragment = CategorySettings()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, categorySettingsFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            111 -> {
                //induvidual ...
                val individualFragment = IndividualFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, individualFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
            112 -> {
                //delivery ...
                val deliveryFragment = DeliveryFragment()
                fm.beginTransaction()
                        .replace(R.id.mainFrame, deliveryFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                fm.executePendingTransactions()
            }
        }
    }

    private fun runtimePermission(): Boolean { //?????? ? ???????????? ??????????

        if (Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE),
                    100)
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) { //???????? ?????? ?? ??????????
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[4] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[5] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[6] == PackageManager.PERMISSION_GRANTED) {

                loadShopFragment()
                setUI()
            } else { //?????????? ????, ???? ?? ??????? ??????????.
                runtimePermission()
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_catalog) { //???????
            changeFragment(Const.CATEGORY_FRAGMENT)
            // Handle the camera action
        } else if (id == R.id.nav_basket) { //????????
            changeFragment(Const.BASKET_FRAGMENT)
        } else if (id == R.id.nav_desires) { //???????
            changeFragment(Const.WISH_FRAGMENT)
        } else if (id == R.id.nav_orders) { // ??? ??????
            changeFragment(Const.ORDER_FRAGMENT)
        } else if (id == R.id.nav_individual) { // ?????????????? ?????, ????? - ??????? ?????? ?? 1 ???
            changeFragment(Const.PAY_FRAGMENT)
        } else if (id == R.id.nav_consulting) { // ?????????? ???
            val call = Uri.parse("tel:" + Const.TEL_NUMBER)
            val surf = Intent(Intent.ACTION_DIAL, call)
            startActivity(surf)
        } else if (id == R.id.delivery) { // ?????????????? ?????, ????? - ??????? ?????? ?? 1 ???
            changeFragment(Const.DELIVERY)
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else if (!doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, "Натисніть ще раз для виходу", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            } else {
                super.onBackPressed()
                return
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }
}
