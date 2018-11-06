package com.artmurka.artmurkaapp.android.views.activities.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.android.views.activities.login.LoginActivity
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.CategoryFragment
import com.artmurka.artmurkaapp.other.FragmentType

import java.util.ArrayList

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Completable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), IMainActivity, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = dispatchingAndroidInjector

    var navController: NavController? = null

    internal var doubleBackToExitPressedOnce = false
    lateinit var tvBigName: TextView
    lateinit var tvSmallName: TextView
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Preferences.init(this)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.card) {
            this.changeFragment(FragmentType.BASKET_FRAGMENT)
        }
        if (item.itemId == R.id.sort) {
            this.changeFragment(FragmentType.CATEGORY_SETTINGS_FRAGMENT)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUI() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val header = navigationView.getHeaderView(0)
        tvSmallName = header.findViewById<TextView>(R.id.tvSmallName)
        tvBigName = header.findViewById<TextView>(R.id.tvBigName)
        btnLogin = header.findViewById<Button>(R.id.btnLogin)
        tvSmallName?.text = Preferences.email
        tvBigName.text = Preferences.name
        btnLogin.text = if (Preferences.isLogin!!) "Вийти" else "Увійти"
        btnLogin.setOnClickListener { v ->
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
                            Preferences.name = "ArtMurka"
                            Preferences.email = "artmurka.com"
                            tvBigName.text = "???-?????"
                            tvSmallName.text = "artmurka.com"
                            Preferences.isLogin = false
                        }
                        .setNegativeButton("??") { dialog, id -> }
                builder.create().show()
            }
        }
    }

    override fun changeFragment(fragment: FragmentType, url: String?, childs: List<Success>?, catName: String?, bndl: Bundle?) {
        var bundle = Bundle()
        when (fragment) {
            FragmentType.CATEGORY_CHILDS_FRAGMENT -> {
                bundle.putParcelableArrayList("childs", childs as ArrayList<out Parcelable>)
                bundle.putString("catName", catName)
                navController?.navigate(R.id.fragmentCategoryChilds, bundle)
            }

            FragmentType.CATEGORY_FRAGMENT ->
                navController?.navigate(R.id.categoryFragment, bundle)

            FragmentType.ITEM_LIST_FRAGMENT -> {
                bundle.putString("url", url)
                bndl?.let {
                    bundle = bndl
                }
                navController?.navigate(R.id.itemListFragment, bundle)
            }

            FragmentType.BASKET_FRAGMENT ->
                navController?.navigate(R.id.basketFragment)

            FragmentType.WISH_FRAGMENT ->
                navController?.navigate(R.id.wishFragment)

            FragmentType.ORDER_FRAGMENT ->
                navController?.navigate(R.id.orderFragment)

            FragmentType.CATEGORY_SETTINGS_FRAGMENT ->
                navController?.navigate(R.id.categorySettings)

            FragmentType.PAY_FRAGMENT ->
                navController?.navigate(R.id.orderFragment)

            FragmentType.DELIVERY ->
                navController?.navigate(R.id.deliveryFragment)
        }
    }

    private fun runtimePermission(): Boolean {
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

                changeFragment(FragmentType.CATEGORY_FRAGMENT)
                setUI()
            } else {
                runtimePermission()
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_catalog) {
            changeFragment(FragmentType.CATEGORY_FRAGMENT)
        } else if (id == R.id.nav_basket) {
            changeFragment(FragmentType.BASKET_FRAGMENT)
        } else if (id == R.id.nav_desires) {
            changeFragment(FragmentType.WISH_FRAGMENT)
        } else if (id == R.id.nav_orders) {
            changeFragment(FragmentType.ORDER_FRAGMENT)
        } else if (id == R.id.nav_individual) {
            changeFragment(FragmentType.PAY_FRAGMENT)
        } else if (id == R.id.nav_consulting) {
            val call = Uri.parse("tel:" + Const.TEL_NUMBER)
            val surf = Intent(Intent.ACTION_DIAL, call)
            startActivity(surf)
        } else if (id == R.id.delivery) {
            changeFragment(FragmentType.DELIVERY)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {

            if(navController?.popBackStack()!!){
                navController?.navigateUp()
            }  else if (!doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, resources.getString(R.string.exit), Toast.LENGTH_SHORT).show()

                Completable.complete().delay(2, TimeUnit.SECONDS).doOnComplete {
                    doubleBackToExitPressedOnce = false
                }.subscribe()
            } else {
                super.onBackPressed()
                return
            }
        }
    }



}
