package com.artmurka.artmurkaapp.android.views.activities.main

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.artmurka.artmurkaapp.android.views.activities.login.LoginActivity
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.other.FragmentType
import com.artmurka.artmurkaapp.presenter.MainPresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.ArrayList
import javax.inject.Inject
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


class MainActivity : AppCompatActivity(), IMainActivity, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: MainPresenter

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = dispatchingAndroidInjector

    var header: View? = null
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.takeView(this)
        Preferences.init(this)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                header?.tvBigName?.text = data?.extras!!.getString("name")
                header?.tvSmallName?.text = data?.extras!!.getString("email")
                header?.btnLogin?.text = "Вийти"
                Preferences.isLogin = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController?.popBackStack()
                return true
            }
            R.id.card -> {
                this.changeFragment(FragmentType.BASKET_FRAGMENT)
                return true
            }
            R.id.sort -> {
                this.changeFragment(FragmentType.CATEGORY_SETTINGS_FRAGMENT)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUI() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.syncState()
        drawer_layout.addDrawerListener(toggle)
        NavigationUI.setupWithNavController(nav_view, navController!!)
        NavigationUI.setupActionBarWithNavController(this, navController!!, drawer_layout)
        toolbar.setNavigationOnClickListener {
            if (!navController?.popBackStack()!!) {
                if (!drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        }

        header = nav_view.getHeaderView(0)
        header?.tvSmallName?.text = Preferences.email
        header?.tvBigName?.text = Preferences.name
        header?.btnLogin?.text = if (Preferences.isLogin!!) "Вийти" else "Увійти"
        header?.btnLogin?.setOnClickListener { v ->
            if (!Preferences.isLogin!!) {
                btnLogin?.text = "Логін"
                val intent = Intent(v.context, LoginActivity::class.java)
                startActivityForResult(intent, 1)
            } else {
//                val builder = AlertDialog.Builder(v.context)
//                builder.setMessage(Preferences.name + ", Ви дійсно хочете вийти з аккаунта?")
//                        .setPositiveButton("Так") { dialog, id ->
//                            Preferences.consumerKey = BuildConfig.CONSUMER_KEY
//                            Preferences.consumerSecret = BuildConfig.CONSUMER_SECRET
//                            Preferences.oauthToken = BuildConfig.OAUTH_TOKEN
//                            Preferences.oauthTokenSecret = BuildConfig.OAUTH_TOKEN_SECRET
//                            Preferences.name = "ArtMurka"
//                            Preferences.email = "artmurka.com"
//                            tvBigName.text = "ArtMurka"
//                            tvSmallName.text = "artmurka.com"
//                            Preferences.isLogin = false
//                        }
//                        .setNegativeButton("Ні") { dialog, id -> }
//                builder.create().show()
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
            FragmentType.CATEGORY_FRAGMENT -> navController?.navigate(R.id.categoryFragment)
            FragmentType.ITEM_LIST_FRAGMENT -> {
                bundle.putString("url", url)
                bndl?.let {
                    bundle = bndl
                }
                navController?.navigate(R.id.itemListFragment, bundle)
            }
            FragmentType.BASKET_FRAGMENT -> navController?.navigate(R.id.basketFragment)
            FragmentType.WISH_FRAGMENT -> navController?.navigate(R.id.wishFragment)
            FragmentType.ORDER_FRAGMENT -> navController?.navigate(R.id.orderFragment)
            FragmentType.CATEGORY_SETTINGS_FRAGMENT -> navController?.navigate(R.id.categorySettings)
            FragmentType.PAY_FRAGMENT -> navController?.navigate(R.id.orderFragment)
            FragmentType.DELIVERY -> navController?.navigate(R.id.deliveryFragment)
        }
    }

    override fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (navController?.currentDestination?.id == null) {
                presenter.onBackPressed()
            } else {
                navController?.navigateUp()
            }
        }
    }

    override fun exit() = this.finish()
}
